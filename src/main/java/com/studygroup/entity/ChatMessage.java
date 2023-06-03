package com.studygroup.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studygroup.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "chat_message")
@Entity
public class ChatMessage extends BaseTimeEntity{

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


    public static ChatMessage getMessage(MessageDto messageDto,ChatRoom chatRoom){
        return ChatMessage.
                builder().
                payload(messageDto.getPayload()).
                sender(messageDto.getSender()).
                chatRoom(chatRoom).
                build();
    }

}
