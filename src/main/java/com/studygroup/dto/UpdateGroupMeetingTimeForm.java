package com.studygroup.dto;


import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class UpdateGroupMeetingTimeForm {

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, fallbackPatterns = {"HH:mm"})
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
