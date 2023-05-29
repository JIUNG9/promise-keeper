package com.studygroup.entity;

import javax.persistence.*;

import com.studygroup.enums.TokenType;
import lombok.*;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "email_token")
public class EmailToken extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

}
