package org.engina.service;

import org.engina.IScoreMapper;
import org.engina.dto.request.ScoreUpdateRequestDto;
import org.engina.exception.ErrorType;
import org.engina.exception.ScoreManagementServiceException;
import org.engina.rabbitmq.model.SaveUserModel;
import org.engina.rabbitmq.producer.UserProducer;
import org.engina.repository.IScoreManagementRepository;
import org.engina.repository.entity.Score;
import org.engina.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreManagementService extends ServiceManager<Score,Long> {
    private final IScoreManagementRepository repository;
    private final UserProducer userProducer;

    public ScoreManagementService(IScoreManagementRepository repository, UserProducer userProducer) {
        super(repository);
        this.repository = repository;
        this.userProducer = userProducer;
    }

    public Score update(ScoreUpdateRequestDto dto) {
        if (dto.getUsername().isEmpty()) {
            throw new ScoreManagementServiceException(ErrorType.SCORE_NOT_FOUND);
        }
        Score score = IScoreMapper.INSTANCE.toScore(dto);
        userProducer.convertAndSend(SaveUserModel.builder()
                .username(score.getUsername())
                .score(score.getScore())
                .build());
        return save(score);
    }

    public List<Score> listScores() {
        return repository.findAllByOrderByScoreDesc();
    }
}
