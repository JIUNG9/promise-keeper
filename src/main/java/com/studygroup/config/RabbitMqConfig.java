package com.studygroup.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  public static final String topicExchange = "chatroom-exchange";

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
  public Binding bindingInquiryRoom() {
    return BindingBuilder.bind(inquiryChatQueue()).to(exchange()).with("chatroom.inquiry.*");
  }

  @Bean
  public Binding bindingGroupRoom() {
    return BindingBuilder.bind(groupChatQueue()).to(exchange()).with("chatroom.group.*");
  }

  @Bean
  public Binding bindingLiveGroupRoom() {
    return BindingBuilder.bind(liveGroupChatQueue()).to(exchange()).with("chatroom.live-group.*");
  }


  @Bean
  public Jackson2JsonMessageConverter jsonMessageConverter() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
    objectMapper.registerModule(dateTimeModule());
    return new Jackson2JsonMessageConverter(objectMapper);
  }


  @Bean
  public com.fasterxml.jackson.databind.Module dateTimeModule() {
    return new JavaTimeModule();
  }


  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }

  @Bean
  ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setHost("localhost");
    connectionFactory.setPort(5672);
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    return connectionFactory;
  }

}