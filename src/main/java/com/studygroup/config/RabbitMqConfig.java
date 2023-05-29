package com.studygroup.config;

import com.rabbitmq.client.impl.Environment;
import com.studygroup.entity.ChatRoom;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitMqConfig {

    public static String topicExchange = "chatroom-exchange";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    Queue inquiryChatQueue() {
        return new Queue("inquiryQueue");
    }

    @Bean
    Queue groupChatQueue() {
        return new Queue("groupQueue");
    }

    @Bean
    Queue liveGroupChatQueue() {
        return new Queue("liveGroupQueue");
    }

    @Bean
    List<Binding> bindings() {
        return List.of(
                BindingBuilder.bind(inquiryChatQueue()).to(exchange()).with("chatroom.inquiry.*"),
                BindingBuilder.bind(groupChatQueue()).to(exchange()).with("chatroom.group.*"),
                BindingBuilder.bind(liveGroupChatQueue()).to(exchange()).with("chatroom.live-group.*")
        );
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
