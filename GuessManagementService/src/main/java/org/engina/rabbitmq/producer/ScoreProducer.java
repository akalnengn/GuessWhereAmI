package org.engina.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.engina.rabbitmq.model.SaveScoreModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreProducer {
    private final RabbitTemplate rabbitTemplate;
    public void convertAndSend(SaveScoreModel model) {
        rabbitTemplate.convertAndSend("direct-exchange-guess","save-binding-key",model);
    }
}
