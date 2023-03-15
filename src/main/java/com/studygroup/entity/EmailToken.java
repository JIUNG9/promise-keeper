package com.studygroup.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@Builder
@Table(name = "email_token")
public class EmailToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Member member;

}
