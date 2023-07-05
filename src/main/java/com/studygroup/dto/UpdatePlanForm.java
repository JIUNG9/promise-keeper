package com.studygroup.dto;

import java.time.LocalTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class UpdatePlanForm {

  public Date startDate;
  public Date endDate;
  public LocalTime startTime;
  public LocalTime endTime;
}
