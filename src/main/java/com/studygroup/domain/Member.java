package com.studygroup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studygroup.enums.Gender;
import com.studygroup.enums.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseTimeEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(length = 128, nullable = false)
  private String email;

  @Column(nullable = false, length = 64)
  private String password;

  @Column(name = "name", length = 45, nullable = false)
  private String name;

  @Column(name = "age", nullable = false)
  private int age;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(columnDefinition = "boolean default false")
  private boolean emailVerified;

  @OneToMany(mappedBy = "member")
  @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.REMOVE})
  private List<EmailToken> emailTokenList;

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<RoomMember> roomMemberList;

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<StudyGroupMember> studyGroupMemberList;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<GrantedAuthority> authorities = new ArrayList<>();
    for (int i = 0; i < Role.values().length; i++) {
      Role role = Role.values()[i];
      if (this.getRole().compareTo(role) == 0) {
        authorities.add(new SimpleGrantedAuthority(role.name()));
      }
    }
    return authorities;
  }


  @Override
  public String getUsername() {
    return this.name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }


  @Override
  public boolean isEnabled() {
    return emailVerified;
  }

  @Override
  public String toString() {
    return "The user name is " + this.name;
  }
}