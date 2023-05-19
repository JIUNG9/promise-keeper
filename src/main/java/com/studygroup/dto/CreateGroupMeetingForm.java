package com.studygroup.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class CreateGroupMeetingForm {


    @NotNull
    @Size(min = 3, max = 20, message ="최소 3자 이상 20자 미만으로 작성해주세요")
    private String subject;
    @NotNull
    @Size(min = 10, max = 100, message ="최소 10자 이상 100자 미만으로 작성해주세요 ")
    private String intro;
    @NotNull
    private List<java.time.DayOfWeek> meetingDateList;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, fallbackPatterns = {"HH:mm"})
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
                .append(", subject='").append(subject).append('\'')
                .append(", meetingDateList='").append(meetingDateList).append('\'')
                .append(", meetingStartTime='").append(meetingStartTime).append('\'')
                .append(", meetingEndTime='").append(meetingEndTime).append('\'')
                .append('}');
        return sb.toString();
    }

}
