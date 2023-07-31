package org.engina.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.engina.rabbitmq.model.SaveGuessModel;
import org.engina.repository.entity.Guess;
import org.engina.service.GuessManagementService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuessConsumer {
    private final GuessManagementService guessManagementService;

    @RabbitListener(queues = "queue-city")
    public void createGuessFromQueue(SaveGuessModel model) {
        guessManagementService.save(Guess.builder()
                .cityid(model.getCityid())
                .cityName(model.getCityName())
                .cityArea(model.getCityArea())
                .hint(model.getHint())
                .photoWithBlur(model.getPhotoWithBlur())
                .photoWithoutBlur(model.getPhotoWithoutBlur())
                .build());
    }
}
