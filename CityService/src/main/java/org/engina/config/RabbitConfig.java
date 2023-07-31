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
    @Bean
    DirectExchange directExchangeCity() {
        return new DirectExchange(directExchangeCity);
    }
    @Bean
    Queue queueCity() {
        return new Queue(queueCity);
    }
    @Bean
    public Binding bindingSaveDirectExchangeForGuess(final Queue queueCity, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueCity).to(directExchange).with(saveBindingKey);
    }
}
