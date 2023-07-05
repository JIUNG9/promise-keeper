package com.studygroup.util.extractor;


import com.studygroup.util.creator.ChatRoomNameGenerator;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

public class ExtractRoomNameByUri {

  private ExtractRoomNameByUri(){

  }

  public static String extractRoomName(HttpServletRequest request, String path)
      throws UnsupportedEncodingException {

    String roomName = null;
    String groupName = null;
    if (path.contains("/inquiry-chats")) {
      roomName = GetPathVariableInFilter.getNonASCIIFirstURI(request, "inquiry-chats/");
    } else if (path.contains("/group-chats")) {
      groupName = GetPathVariableInFilter.getFirstPathVarAfterGivenURI(request, "groups/");
      roomName = ChatRoomNameGenerator.getGroupChatRoomName(groupName);
    } else {
      groupName = GetPathVariableInFilter.getFirstPathVarAfterGivenURI(request, "groups/");
      roomName = ChatRoomNameGenerator.getLiveChatRoomName(groupName);

    }
    return roomName;
  }

}
