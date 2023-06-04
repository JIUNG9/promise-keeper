package com.studygroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class MessageLog {
    public String payload;
    public String sender;
}
