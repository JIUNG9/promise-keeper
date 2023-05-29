package com.studygroup.controller;



import com.studygroup.util.WebcamUtils;
import com.studygroup.util.constant.SendVideoSocketConstant;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class LiveMeetingController {


    private static final Logger logger = LoggerFactory
            .getLogger(LiveMeetingController.class);

    @PostMapping("/api/users/live-meeting")
    public ResponseEntity<Object> sendWebCamVideoData() {

        WebcamUtils.startSendingVideo(SendVideoSocketConstant.DOMAIN+ SendVideoSocketConstant.WEB_SOCKET_END_POINT);
        logger.info("can't access web cam data");

            return ResponseEntity.status(HttpStatus.OK).body("successfully send web camera video data to server");

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
