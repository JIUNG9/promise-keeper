package com.studygroup.util.convertor;

public class ObjectToLong {

  private ObjectToLong(){

  }
  public static Long convert(Object o) {
    return Long.valueOf(o.toString());
  }
}
