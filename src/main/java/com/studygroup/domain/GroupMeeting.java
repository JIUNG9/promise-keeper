package com.studygroup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
  @JsonIgnore
  @OneToMany(mappedBy = "groupMeeting", cascade = CascadeType.REMOVE)
  private List<MeetingDay> dayOfWeekList = new java.util.ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private StudyGroup studyGroup;

}
