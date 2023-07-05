package com.studygroup.dto;

<<<<<<< HEAD
import java.time.LocalTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
=======
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

<<<<<<< HEAD
=======
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.List;

>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
@RequiredArgsConstructor
@Getter
public class CreateGroupMeetingForm {


<<<<<<< HEAD
  @NotNull
  @Size(min = 3, max = 20, message = "최소 3자 이상 20자 미만으로 작성해주세요")
  private String subject;
  @NotNull
  @Size(min = 10, max = 100, message = "최소 10자 이상 100자 미만으로 작성해주세요 ")
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
=======
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
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91

}
