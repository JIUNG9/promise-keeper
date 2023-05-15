package com.studygroup.dto;

import com.studygroup.entity.DayOfWeek;
import com.studygroup.entity.StudyGroup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class CreateGroupMeetingForm {

    @NotNull
    private String groupName;
    @NotNull
    private String subject;
    @NotNull
    private String intro;
    @NotNull
    private List<java.time.DayOfWeek> meetingDateList;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, fallbackPatterns = {"HH:mm"})
    @NotNull
    private LocalDateTime meetingStartTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, fallbackPatterns = {"HH:mm"})
    @NotNull
    private LocalDateTime meetingEndTime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CreateGroupMeetingForm")
                .append("{")
                .append(", groupName='").append(groupName).append('\'')
                .append(", subject='").append(subject).append('\'')
                .append(", meetingDateList='").append(meetingDateList).append('\'')
                .append(", meetingStartTime='").append(meetingStartTime).append('\'')
                .append(", meetingEndTime='").append(meetingEndTime).append('\'')
                .append('}');
        return sb.toString();
    }

}
