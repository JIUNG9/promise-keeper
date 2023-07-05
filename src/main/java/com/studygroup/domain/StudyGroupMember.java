package com.studygroup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studygroup.enums.GroupRole;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "study_group_member")
public class StudyGroupMember extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 256, nullable = false)
  private String nickName;

  @Column(length = 256, nullable = false)
  private String intro;


  @Column(columnDefinition = "integer default 0", nullable = false)
  private int warnCount;


  @Enumerated(EnumType.STRING)
  private GroupRole groupRole;

  @JsonIgnore
  @OneToMany(mappedBy = "studyGroupMember", cascade = {CascadeType.REMOVE,
      CascadeType.PERSIST}, orphanRemoval = true)
  private List<StudyGroupMemberPlan> studyGroupMemberPlanList;

  @ManyToOne
  @JoinColumn
  private StudyGroup studyGroup;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Member member;


}

