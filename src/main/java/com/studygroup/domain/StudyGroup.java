package com.studygroup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studygroup.enums.MainCategory;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "study_group")
public class StudyGroup extends BaseTimeEntity {

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

  @JsonIgnore
  @Builder.Default
  @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<GroupMeeting> groupMeetingList = new java.util.ArrayList<>();

  @JsonIgnore
  @Builder.Default
  @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<StudyGroupMember> studyGroupMemberList = new java.util.ArrayList<>();


}
