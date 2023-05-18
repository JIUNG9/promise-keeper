package com.studygroup.dto;


import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
public class UpdateGroupMeetingTimeForm {

    @NotNull
    private LocalTime meetingStartTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, fallbackPatterns = {"HH:mm"})
    @NotNull
    private LocalTime meetingEndTime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CreateGroupMeetingForm")
                .append("{")
                .append(", meetingStartTime='").append(meetingStartTime).append('\'')
                .append(", meetingEndTime='").append(meetingEndTime).append('\'')
                .append('}');
        return sb.toString();
    }
}
