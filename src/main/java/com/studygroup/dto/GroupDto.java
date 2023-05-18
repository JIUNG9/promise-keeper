package com.studygroup.dto;

import com.studygroup.enums.MainCategory;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private MainCategory mainCategory;
    private String subject;
    private String name;
    private String intro;
}
