package com.studygroup.dto;

import com.studygroup.entity.StudyGroup;
import com.studygroup.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class MyInfoDto {
    String email;
    String name;
    int age;
    Gender gender;
    List<String> groupName;
}
