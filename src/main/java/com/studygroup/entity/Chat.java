package com.studygroup.entity;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "chat")
@Entity
public class Chat extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String payload;

    @ManyToOne
    @JoinColumn
    private ChatRoom chatRoom;


}
