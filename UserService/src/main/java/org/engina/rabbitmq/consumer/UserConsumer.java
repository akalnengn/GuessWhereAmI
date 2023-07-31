package org.engina.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.engina.rabbitmq.model.SaveUserModel;
import org.engina.repository.entity.User;
import org.engina.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConsumer {
    private final UserService userService;

    @RabbitListener(queues = "queue-score")
    public void createUserFromQueue(SaveUserModel model) {
        userService.save(User.builder()
                .score(model.getScore())
                .build());
    }
}
