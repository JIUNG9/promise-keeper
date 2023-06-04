package com.studygroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class EnterRoomDto {
    Long roomId;
    List<MessageLog> messageLogList;
}
