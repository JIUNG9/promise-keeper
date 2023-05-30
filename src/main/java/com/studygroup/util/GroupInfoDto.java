package com.studygroup.util;

import com.studygroup.enums.MainCategory;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfoDto {
    private MainCategory mainCategory;
    private String subject;
    private String name;
    private String intro;
    private int theNumberOfMember;
}
