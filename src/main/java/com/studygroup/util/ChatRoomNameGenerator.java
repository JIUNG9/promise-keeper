package com.studygroup.util;

public class ChatRoomNameGenerator {

    private static StringBuilder sb = new StringBuilder();

    public static String getInquiryRoomName(String requestUserName, String groupName ){
        sb.append(requestUserName);
        sb.append("님이");
        sb.append(" ");
        sb.append("요청한");
        sb.append(" ");
        sb.append(groupName);
        sb.append(" ");
        sb.append("문의방");

        return sb.toString();
    }

    public static String getGroupChatRoomName(String groupName) {
        sb.append(groupName);
        sb.append(" ");
        sb.append("단체 채팅방");

        return sb.toString();
    }

    public static String getLiveChatRoomName(String groupName) {
        sb.append(groupName);
        sb.append(" ");
        sb.append("실시간 그룹 채팅방");

        return sb.toString();
    }



}
