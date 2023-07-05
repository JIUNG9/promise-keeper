package com.studygroup.service.chat;

import com.studygroup.config.RabbitMqConfig;
import com.studygroup.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMessageServiceImpl implements SendMessageService {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void sendMessage(String routingKey, ChatMessage chatMessage) {

    rabbitTemplate.
        convertAndSend(RabbitMqConfig.topicExchange,
            routingKey,
            chatMessage
        );
  }
}
