package com.studygroup.entity;

import javax.persistence.*;

import com.studygroup.enums.Gender;
import com.studygroup.enums.MainCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name ="study_group")
public class StudyGroup extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory;

    @Column(length = 256, nullable = false)
    private String subject;

    @Column(length = 256, nullable = false)
    private String name;

    @Column(length = 256, nullable = false)
    private String info;

    @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<GroupMeeting> groupMeetingList = new java.util.ArrayList<>();

    @OneToMany(mappedBy ="studyGroup", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StudyGroupMember> studyGroupMemberList = new java.util.ArrayList<>();




}
