package com.studygroup.util.constant;

public class RoutingKeyGenerator {

    public static String inquiryRoutingKey(String roomName){
       return  "chatroom.inquiry." + roomName;
    }

    public static String groupChatRoomRoutingKey(String roomName) {
        return "chatroom.group." + roomName;
    }

    public static String liveGroupChatRoutingKey(String roomName) {
        return "chatroom.live-group." + roomName;
    }
}
