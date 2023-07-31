package org.engina.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.engina.rabbitmq.model.SaveUserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProducer {
    private final RabbitTemplate rabbitTemplate;
    public void convertAndSend(SaveUserModel model) {
        rabbitTemplate.convertAndSend("direct-exchange-score","save-binding-key",model);
    }
}
