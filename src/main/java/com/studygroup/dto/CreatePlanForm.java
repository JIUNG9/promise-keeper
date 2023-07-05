package com.studygroup.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.studygroup.util.json.DateDeserialization;
import java.time.LocalTime;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
public class CreatePlanForm {

  @NotNull
  private String title;
  @NotNull
  @JsonDeserialize(using = DateDeserialization.class)
  private Date startDate;
  @NotNull
  @JsonDeserialize(using = DateDeserialization.class)
  private Date endDate;
  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, fallbackPatterns = {"HH:mm"})
  private LocalTime startTime;
  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, fallbackPatterns = {"HH:mm"})
  private LocalTime endTime;
  private String payload;
}
