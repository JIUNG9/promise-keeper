package com.studygroup.domain;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "study_group_member_plan")
public class StudyGroupMemberPlan extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private java.util.Date startDate;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private java.util.Date endDate;

  private LocalTime startTime;

  private LocalTime endTime;

  @Column(columnDefinition = "boolean default false")
  private boolean is_achieved;

  private String payload;

  @JoinColumn
  @ManyToOne(fetch = FetchType.LAZY)
  private StudyGroupMember studyGroupMember;
}
