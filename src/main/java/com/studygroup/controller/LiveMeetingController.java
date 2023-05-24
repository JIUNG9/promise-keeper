package com.studygroup.controller;



import com.studygroup.service.chatroom.CheckUserIsInChatRoomService;
import com.studygroup.util.WebcamUtils;
import com.studygroup.util.constant.SendVideoSocketConstant;
import com.studygroup.util.constant.ErrorCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LiveMeetingController {

    private final CheckUserIsInChatRoomService checkUserIsInLiveMeetingRoomService;

    public LiveMeetingController(@Qualifier("CheckUserIsInLiveMeetingRoomService") CheckUserIsInChatRoomService checkUserIsInLiveMeetingRoomService) {
        this.checkUserIsInLiveMeetingRoomService = checkUserIsInLiveMeetingRoomService;
    }

    private static final Logger logger = LoggerFactory
            .getLogger(LiveMeetingController.class);

    //    @PostMapping("/api/users/{groupName}/{roomId}/live-meeting")
    @PostMapping("/api/test")
    public ResponseEntity<Object> sendWebCamVideoData(@AuthenticationPrincipal Object memberId
//                                                      @PathVariable String groupName,
//                                                      @PathVariable UUID roomId
    ) {

        WebcamUtils.startSendingVideo(SendVideoSocketConstant.DOMAIN
                + SendVideoSocketConstant.WEB_SOCKET_END_POINT);
        logger.info("can't access web cam data");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.CAN_NOT_ACCESS_WEB_CAM);

//            return ResponseEntity.status(HttpStatus.OK).body("successfully send web camera video data to server");

//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.USER_IS_NOT_IN_CHAT_ROOM);

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
