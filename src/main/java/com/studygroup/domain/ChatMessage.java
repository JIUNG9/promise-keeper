package com.studygroup.domain;

import com.studygroup.dto.MessageDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "chat_message")
@Entity
public class ChatMessage extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Column(length = 255, nullable = false)
  private String sender;


  @Column(length = 255, nullable = false)
  private String payload;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private ChatRoom chatRoom;


  public static ChatMessage getMessage(MessageDto messageDto, ChatRoom chatRoom) {
    return ChatMessage.
        builder().
        payload(messageDto.getPayload()).
        sender(messageDto.getSender()).
        chatRoom(chatRoom).
        build();
  }

}
