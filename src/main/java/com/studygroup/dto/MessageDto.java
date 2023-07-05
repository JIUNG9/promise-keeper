package com.studygroup.dto;

import com.studygroup.domain.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class MessageDto {

  protected String payload;
  protected String sender;

  public static MessageDto convert(ChatMessage chatMessage) {
    return
        MessageDto.
            builder().
            sender(chatMessage.getSender()).
            payload(chatMessage.getPayload()).
            build();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    sb.append("Message: \n");
    sb.append("payload: ");
    sb.append(payload);
    sb.append("\n");
    sb.append("sender: ");
    sb.append(sender);
    sb.append("\n");
    return sb.toString();
  }
}
