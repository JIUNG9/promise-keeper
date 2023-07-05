package com.studygroup.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

abstract class ApiSubError {

}

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
class ApiValidationError extends ApiSubError {

  private String message;
  private String object;
  private String field;
  private Object rejectedValue;


}

