package com.studygroup.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studygroup.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name ="chat_room")
@Builder
public class ChatRoom extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<RoomMember> roomMember;

    @JsonIgnore
    @OneToMany(mappedBy = "chatRoom" )
    private List<ChatMessage> chatList = new java.util.ArrayList<>();

}
