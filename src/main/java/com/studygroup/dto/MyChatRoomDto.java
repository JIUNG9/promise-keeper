package com.studygroup.dto;

import com.studygroup.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class MyChatRoomDto {
    private String roomName;
    private RoomType roomType;

}
