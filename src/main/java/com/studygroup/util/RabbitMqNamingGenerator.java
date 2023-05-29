package com.studygroup.util;

public class RabbitMqNamingGenerator {

    public static String getGroupChatQueueAndKeyName(String groupName){
        StringBuilder sb = new StringBuilder();
        sb.append("group_chat_queue_");
        sb.append(groupName);

        return sb.toString();
    }


    public static String getLiveGroupChatQueueAndKeyName(String groupName) {
        StringBuilder sb = new StringBuilder();
        sb.append("group_live_chat_queue_");
        sb.append(groupName);

        return sb.toString();
    }

    public static String getInquiryChatQueueAndKeyName(String requestMemberName ,String groupName) {
        StringBuilder sb = new StringBuilder();
        sb.append("inquiry_chat_queue_from");
        sb.append(requestMemberName);
        sb.append("_");
        sb.append("to");
        sb.append(groupName);

        return sb.toString();
    }
}
