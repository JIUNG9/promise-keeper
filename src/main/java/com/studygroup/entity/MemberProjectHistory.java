package com.studygroup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class MemberProjectHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean succeeded;

    @Column(length = 64, nullable = false)
    private String teamName;

    @ManyToOne
    @JoinColumn
    private ProjectTeamMember projectTeamMember;








}
