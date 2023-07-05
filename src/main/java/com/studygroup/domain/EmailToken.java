package com.studygroup.domain;

import com.studygroup.auth.token.TokenData;
import com.studygroup.enums.TokenType;
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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "email_token")
public class EmailToken extends BaseTimeEntity implements TokenData
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "value")
  private String value;

  @Enumerated(EnumType.STRING)
  private TokenType tokenType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Member member;

}
