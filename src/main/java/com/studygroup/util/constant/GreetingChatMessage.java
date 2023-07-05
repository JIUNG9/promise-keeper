package com.studygroup.util.constant;

public class GreetingChatMessage {

<<<<<<< HEAD
  public static String greetMessage(String name) {
    StringBuilder sb = new StringBuilder();
    sb.append(name);
    sb.append("님이");
    sb.append(" ");
    sb.append("입장하십니다");
    return sb.toString();
  }

  public static String exitMessage(String name) {
    StringBuilder sb = new StringBuilder();
    sb.append(name);
    sb.append("님이");
    sb.append(" ");
    sb.append("퇴장하십니다.");
    return sb.toString();
  }
=======
    public static String greetMessage(String name){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("님이");
        sb.append(" ");
        sb.append("입장하십니다");
        return sb.toString();
    }

    public static String exitMessage(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("님이");
        sb.append(" ");
        sb.append("퇴장하십니다.");
        return sb.toString();
    }
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
}
