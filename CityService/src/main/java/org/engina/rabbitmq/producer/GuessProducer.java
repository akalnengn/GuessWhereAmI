package org.engina.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.engina.rabbitmq.model.SaveGuessModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuessProducer {
    private final RabbitTemplate rabbitTemplate;

    public void convertAndSend(SaveGuessModel model) {
        rabbitTemplate.convertAndSend("direct-exchange-city","save-binding-key",model);
    }
}
