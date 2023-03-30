package com.studygroup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


enum Gender{
    male,
    female;
}

@NoArgsConstructor
@Entity
@Data
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false)
    private String subject;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 256, nullable = false)
    private String meetingDate;

    @Column(nullable = false)
    private int limitMemberNumber;

    @Temporal(TemporalType.TIME)
    private java.util.Date meeting_start_time;

    @Temporal(TemporalType.TIME)
    private java.util.Date meeting_end_time;

    @Column(length = 256, nullable = false)
    private String intro;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    @JoinColumn
    private List<StudyGroupMember> studyGroupMemberList;




}
