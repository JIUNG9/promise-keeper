package com.studygroup.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studygroup.enums.RoomType;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "chat_room")
@Builder
public class ChatRoom extends BaseTimeEntity {

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
  @OneToMany(mappedBy = "chatRoom")
  private List<ChatMessage> chatList = new java.util.ArrayList<>();

}
