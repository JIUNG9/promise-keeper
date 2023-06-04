package com.studygroup.util.constant;

public class GreetingChatMessage {

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
}
