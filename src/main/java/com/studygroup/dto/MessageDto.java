package com.studygroup.dto;

<<<<<<< HEAD
import com.studygroup.domain.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
=======
import com.studygroup.entity.ChatMessage;
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
<<<<<<< HEAD
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
=======
public class MessageDto {
    private String payload;
    private String sender;


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
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
}
