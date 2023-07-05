package com.studygroup.dto;

import com.studygroup.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class ChatRoomDto {

  private String roomName;
  private RoomType roomType;

}
