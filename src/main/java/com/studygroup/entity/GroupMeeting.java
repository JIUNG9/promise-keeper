package com.studygroup.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "group_meeting")
@Builder
public class GroupMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 256, nullable = false)
    private String subject;

    @Column(length = 256, nullable = false)
    private String intro;

    private LocalTime meetingStartTime;

    private LocalTime meetingEndTime;

    @Builder.Default
    @OneToMany(mappedBy = "groupMeeting", cascade = CascadeType.REMOVE)
    private List<MeetingDay> dayOfWeekList = new java.util.ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)    @JoinColumn
    private StudyGroup studyGroup;

}
