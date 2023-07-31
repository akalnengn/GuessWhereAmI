package org.engina.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    String directExchangeGuess = "direct-exchange-guess";
    String queueGuess = "queue-guess";
    String directExchangeScore = "direct-exchange-score";
    String queueScore = "queue-score";
    String saveBindingKey = "save-binding-key";
    @Bean
    DirectExchange directExchangeGuess() {
        return new DirectExchange(directExchangeGuess);
    }
    @Bean
    Queue queueGuess() {
        return new Queue(queueGuess);
    }
    @Bean
    DirectExchange directExchangeScore() {
        return new DirectExchange(directExchangeScore);
    }
    @Bean
    Queue queueScore() {
        return new Queue(queueScore);
    }
    @Bean
    public Binding bindingSaveDirectExchangeForScore(final Queue queueGuess, final DirectExchange directExchangeGuess) {
        return BindingBuilder.bind(queueGuess).to(directExchangeGuess).with(saveBindingKey);
    }
    @Bean
    public Binding bindingSaveDirectExchangeForUser(final Queue queueScore, final DirectExchange directExchangeScore) {
        return BindingBuilder.bind(queueScore).to(directExchangeScore).with(saveBindingKey);
    }
}
