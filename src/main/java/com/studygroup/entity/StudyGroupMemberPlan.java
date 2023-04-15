package com.studygroup.entity;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class StudyGroupMemberPlan extends BaseTimeEntity{

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
    private StudyGroupMember studyGroupMember;
}
