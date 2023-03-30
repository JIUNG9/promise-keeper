package com.studygroup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class GroupMemberSchedule extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIME)
    private java.util.Date date;

    @Temporal(TemporalType.TIME)
    private java.util.Date time;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean is_achieved;

    @ManyToOne
    @JoinColumn
    private StudyGroupMember GroupMemberSchedule;
}
