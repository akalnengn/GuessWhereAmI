package org.engina.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    String directExchangeCity = "direct-exchange-city";
    String queueCity = "queue-city";
    String saveBindingKey = "save-binding-key";
    String directExchangeGuess = "direct-exchange-guess";
    String queueGuess = "queue-guess";
    @Bean
    DirectExchange directExchangeCity() {
        return new DirectExchange(directExchangeCity);
    }
    @Bean
    Queue queueCity() {
        return new Queue(queueCity);
    }
    @Bean
    DirectExchange directExchangeGuess() {
        return new DirectExchange(directExchangeGuess);
    }
    @Bean
    Queue queueGuess() {
        return new Queue(queueGuess);
    }
    @Bean
    public Binding bindingSaveDirectExchangeForGuess(final Queue queueGuess, final DirectExchange directExchangeGuess) {
        return BindingBuilder.bind(queueGuess).to(directExchangeGuess).with(saveBindingKey);
    }
    @Bean
    public Binding bindingSaveDirectExchangeForScore(final Queue queueGuess, final DirectExchange directExchangeGuess) {
        return BindingBuilder.bind(queueGuess).to(directExchangeGuess).with(saveBindingKey);
    }
}
