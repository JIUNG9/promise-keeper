package com.studygroup.controller;



import com.studygroup.service.chatroom.CheckUserIsInChatRoomService;
import com.studygroup.service.chatroom.CheckUserIsInLiveMeetingRoomService;
import com.studygroup.util.WebcamUtils;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.constant.ObjectToLong;
import com.studygroup.util.constant.ServerUrl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class LiveMeetingController {

    private final CheckUserIsInChatRoomService checkUserIsInLiveMeetingRoomService;

    public LiveMeetingController(@Qualifier("CheckUserIsInLiveMeetingRoomService") CheckUserIsInChatRoomService checkUserIsInLiveMeetingRoomService) {
        this.checkUserIsInLiveMeetingRoomService = checkUserIsInLiveMeetingRoomService;
    }

    private static final Logger logger = LoggerFactory
            .getLogger(LiveMeetingController.class);

    @PostMapping("/api/user/{groupName}/{roomId}/live-meeting")
    public ResponseEntity<Object> sendWebCamVideoData(@AuthenticationPrincipal Object memberId,
                                                      @PathVariable String groupName,
                                                      @PathVariable UUID roomId
                                                      ) {


        boolean userIsInChatRoom = checkUserIsInLiveMeetingRoomService.
                check(ObjectToLong.convert(memberId), roomId);

        if (userIsInChatRoom) {
            logger.info("user is in the chat room");
            if (!WebcamUtils.sendWebcamVideo
                    (ServerUrl.FLASK_SERVER_URL + ServerUrl.SEND_WEB_CAM_DATA_URL,
                            ObjectToLong.convert(memberId),
                            roomId)) {
                logger.info("can't access web cam data");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.CAN_NOT_ACCESS_WEB_CAM);
            }
            return ResponseEntity.status(HttpStatus.OK).body("successfully send web camera video data to server");

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.USER_IS_NOT_IN_CHAT_ROOM);

    }
}

//    @GetMapping("/api/user/{groupName}/{roomId}/live-meeting")
//    public ResponseEntity<Object> getUserStatusFromServer(@RequestBody @Valid UserInfoInChatRoomForm userInfoInChatRoomForm) {

//        logger.info(userInfoInChatRoomForm.toString());
//        UUID roomId = userInfoInChatRoomForm.getRoomId();
//        Long memberId = userInfoInChatRoomForm.getUserId();
//
//        boolean userIsInChatRoom = checkUserIsInLiveMeetingRoomService.check(memberId, roomId);
//        UpdateUserWarnCountService updateUserWarnCount.increaseCount();// => updateGroupMember comply the OCP
//        SendMessageGroupMemberStatusToChatRoom sendMessageGroupMemberStatusToChatRoom.send()
//        logger.info(userInfoInChatRoomForm.toString());
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("User is successfully saved but need to verify the email");


//}
