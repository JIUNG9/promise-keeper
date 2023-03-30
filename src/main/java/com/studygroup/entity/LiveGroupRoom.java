package com.studygroup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class LiveGroupRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean is_achieved;

    @OneToOne(mappedBy = "liveGroupRoom")
    private LiveMeeting liveMeeting;

    @OneToOne(mappedBy = "liveGroupChat")
    private LiveGroupChat liveGroupChat;
}
