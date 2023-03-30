package com.studygroup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class StudyGroupMember extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false)
    private String nick_name;

    @Column(columnDefinition = "boolean default false",nullable = false)
    private Boolean isLeader;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int warnCount;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean accessPermission;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<GroupMemberSchedule> groupMemberScheduleList = new java.util.ArrayList<>();

    @ManyToOne
    @JoinColumn
    private StudyGroup studyGroup;

    @OneToOne(mappedBy = "studyGroupMember")
    StudyGroupMember studyGroupMember;

    @ManyToOne
    @JoinColumn
    private Member member;

}

