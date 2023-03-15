package com.studygroup.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class ProjectTeam extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(length = 128, nullable = false)
    private String subject;

    @Column(length = 255, nullable = false)
    private String teamInfo;

    @Nullable
    @Column(length = 64, nullable = false)
    private String frontTech;

    @Nullable
    @Column(length = 64, nullable = false)
    private String backendTech;

    @Nullable
    @Column(length = 64, nullable = false)
    private String aiTech;

    @Nullable
    @Column(length = 64, nullable = false)
    private String cloudTech;

    @Nullable
    @Column(length = 64, nullable = false)
    private String databaseTech;


    @OneToMany(mappedBy = "projectTeam")
    private List<ProjectTeamMember> projectTeamMember;


}
