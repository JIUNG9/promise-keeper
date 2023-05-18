package com.studygroup.entity;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name ="meeting_day")
public class MeetingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated
    @Column(name = "day_of_week")
    private java.time.DayOfWeek dayOfWeek;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn
    private GroupMeeting groupMeeting;
}
