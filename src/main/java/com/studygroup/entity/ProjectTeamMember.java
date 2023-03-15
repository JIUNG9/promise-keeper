package com.studygroup.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class ProjectTeamMember extends BaseTimeEntity{

    @Id
    private Long studentId;

    @Nullable
    @Enumerated(EnumType.STRING)
    private teamRole teamRole;

    @Nullable
    @Column
    private Boolean isLeader;
    //there is a duplication check
    @Column(length = 64, nullable = false)
    private String nickName;

    @Column(nullable = false)
    private int grade;

    @Column(length = 255, nullable = false)
    private String memberInfo;

    @Column(length = 255, nullable = false)
    private String preferenceTech;

    @ManyToOne
    @JoinColumn(nullable = true)
    private ProjectTeam projectTeam;

    @OneToMany(mappedBy = "projectTeamMember")
    private List<MemberProjectHistory>memberProjectHistoryList;

    @OneToOne
    @JoinColumn
    private Member member;




}
