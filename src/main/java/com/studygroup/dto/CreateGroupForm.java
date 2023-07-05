package com.studygroup.dto;

import com.studygroup.enums.MainCategory;
<<<<<<< HEAD
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
=======
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91

@RequiredArgsConstructor
@Getter
public class CreateGroupForm {

<<<<<<< HEAD
  @NotNull
  private MainCategory mainCategory;

  @NotNull
  @Size(min = 2, max = 10, message = "주제의 최소 길이는 2자 최대는 10자입니다.")
  private String subject;

  @NotNull
  @Size(min = 2, max = 10, message = "닉네임 최소 길이는 2자 최대는 10자입니다.")
  private String nickName;

  @NotNull
  @Size(min = 2, max = 10, message = "그룹 이름의 최소 길이는 2자이고 최대는 10자입니다.")
  private String name;

  @NotNull
  @Size(min = 10, max = 200, message = "그룹의 정보를 최소 10자이상 100자 미만으로 작성해주세요")
  private String info;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CreateGroupForm")
        .append("{")
        .append(", mainCategory='").append(mainCategory).append('\'')
        .append(", subject='").append(subject).append('\'')
        .append(", name='").append(name).append('\'')
        .append('}');
    return sb.toString();
  }
=======
    @NotNull
    private MainCategory mainCategory;

    @NotNull
    @Size(min =2, max = 10 ,message = "주제의 최소 길이는 2자 최대는 10자입니다.")
    private String subject;

    @NotNull
    @Size(min = 2, max = 10, message = "닉네임 최소 길이는 2자 최대는 10자입니다.")
    private String nickName;

    @NotNull
    @Size(min = 2, max = 10, message = "그룹 이름의 최소 길이는 2자이고 최대는 10자입니다.")
    private String name;

    @NotNull
    @Size(min = 10, max = 200, message = "그룹의 정보를 최소 10자이상 100자 미만으로 작성해주세요")
    private String info;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CreateGroupForm")
                .append("{")
                .append(", mainCategory='").append(mainCategory).append('\'')
                .append(", subject='").append(subject).append('\'')
                .append(", name='").append(name).append('\'')
                .append('}');
        return sb.toString();
    }
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
}
