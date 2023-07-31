package org.engina.service;

import org.engina.exception.ErrorType;
import org.engina.exception.GuessManagementServiceException;
import org.engina.rabbitmq.model.SaveScoreModel;
import org.engina.rabbitmq.producer.ScoreProducer;
import org.engina.repository.IGuessManagementRepository;
import org.engina.repository.entity.Guess;
import org.engina.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GuessManagementService extends ServiceManager<Guess, Long> {
    private final IGuessManagementRepository repository;
    private final ScoreProducer scoreProducer;

    public GuessManagementService(IGuessManagementRepository repository, ScoreProducer scoreProducer) {
        super(repository);
        this.repository = repository;
        this.scoreProducer = scoreProducer;
    }

    public String chooseQuestion(Long questionId) { //bu metod city-service'sinden gelen kaydın sadece hint ve blur'lu fotosunu kullanıcıya dönüyor.Böylelikle kullanıcı cevaplamak istediği soruyu seçebiliyor.
        String[] arr = new String[2];
        arr[0] = repository.findById(questionId).get().getHint();
        arr[1] = repository.findById(questionId).get().getPhotoWithBlur();
        return Arrays.toString(arr);
    }

    int denemeHakkı = 5;
    int point = 0;
    public String doGuess(Long id, Long userid, String answer) { //Kullanıcı yanlış cevap verdikçe -5 puan doğru cevap verirse +10 puan tblscore tablosuna rabbitmq ile ekleniyor.
        if (repository.findById(id).isEmpty()) {
            throw new GuessManagementServiceException(ErrorType.QUESTION_NOT_FOUND);
        }
        while (denemeHakkı > 0) {
            if (!repository.findById(id).get().getCityName().equalsIgnoreCase(answer)) {
                point -= 5;
                denemeHakkı--;
                return "Yanlış cevap lütfen tekrar deneyiniz.Kalan deneme: " + denemeHakkı;
            } else if (repository.findById(id).get().getCityName().equalsIgnoreCase(answer)) {
                point += 10;
                scoreProducer.convertAndSend(SaveScoreModel.builder()
                        .score(point)
                        .userid(userid)
                        .build());
                return "Tebrikler doğru cevap!";
            }
        }
        scoreProducer.convertAndSend(SaveScoreModel.builder()
                .score(point)
                .userid(userid)
                .build());
        return "Tahmin hakkınız bitti.";
    }
}
