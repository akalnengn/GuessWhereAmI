package org.engina.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.engina.rabbitmq.model.SaveScoreModel;
import org.engina.repository.entity.Score;
import org.engina.service.ScoreManagementService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreConsumer {
    private final ScoreManagementService scoreManagementService;

    @RabbitListener(queues = "queue-guess")
    public void createScoreFromQueue(SaveScoreModel model) {
        scoreManagementService.save(Score.builder()
                .userid(model.getUserid())
                .score(model.getScore())
                .build());
    }

}
