package com.studygroup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class LiveMeeting extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean camera_status;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean mic_status;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean is_sleep;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean head_down;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean watching_phone;

    @Temporal(TemporalType.TIME)
    private java.util.Date meeting_start_time;

    @Temporal(TemporalType.TIME)
    private java.util.Date meeting_end_time;

    @OneToOne
    @JoinColumn
    private StudyGroupMember studyGroupMember;

    @OneToOne
    @JoinColumn
    private LiveGroupRoom liveGroupRoom;
}
