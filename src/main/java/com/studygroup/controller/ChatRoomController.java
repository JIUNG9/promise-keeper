package com.studygroup.controller;

import com.studygroup.dto.MessageLog;
import com.studygroup.dto.MyChatRoomDto;
import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.exception.ApiError;
import com.studygroup.service.chatroom.FindGroupChatRoomService;
import com.studygroup.service.chatroom.*;
import com.studygroup.service.groupmember.FindGroupAdminMemberService;
import com.studygroup.service.groupmember.LeaveGroupChatRoomService;
import com.studygroup.service.user.RetrieveMemberByAuthPrinciple;
import com.studygroup.util.WebcamUtils;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.ObjectToLong;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChatRoomController {

    private  FindGroupChatRoomService findLiveGroupChatRoom;
    private  FindGroupChatRoomService findGroupChatRoom;
    private  GetInquiryChatHistoryService getInquiryChatHistoryService;
    private  GetGroupChatHistoryService getGroupChatHistoryService;
    private  RetrieveMyChatRoomListService retrieveMyChatRoomList;
    private  FindGroupAdminMemberService findGroupAdminMember;
    private  FindInquiryChatRoomService findInquiryChatRoomService;
    private  CreateOneToOneChatRoomService createInquiryChatRoom;
    private  CreateChatRoomService createGroupChatRoom;
    private  CreateChatRoomService createLiveGroupChatRoom;
    private  AddChatRoomMember addChatRoomMember;
    private  RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple;
    private  LeaveGroupChatRoomService leaveGroupChatRoomService;
    private  LeaveGroupChatRoomService leaveLiveGroupChatRoomService;
    private  LeaveInquiryChatRoomService leaveInquiryChatRoomService;
    private  DeleteChatRoomService deleteInquiryChatRoomService;
    private  DeleteChatRoomService deleteLiveChatRoomService;
    private  CountChatRoomMember countChatRoomMember;

    public ChatRoomController(@Qualifier("FindLiveGroupChatRoomService") FindGroupChatRoomService findLiveGroupChatRoom,
                              @Qualifier("FindGroupChatRoomServiceImpl") FindGroupChatRoomService findGroupChatRoom,
                              GetInquiryChatHistoryService getInquiryChatHistoryService,
                              GetGroupChatHistoryService getGroupChatHistoryService,
                              @Qualifier("RetrieveMyChatRoomListServiceImpl") RetrieveMyChatRoomListService retrieveMyChatRoomList,
                              @Qualifier("FindGroupAdminMemberServiceImpl") FindGroupAdminMemberService findGroupAdminMember,
                              FindInquiryChatRoomService findInquiryChatRoomService,
                              @Qualifier("CreateInquiryChatRoomService") CreateOneToOneChatRoomService createInquiryChatRoom,
                              @Qualifier("CreateGroupChatRoomService") CreateChatRoomService createGroupChatRoom,
                              @Qualifier("CreateLiveChatRoomService") CreateChatRoomService createLiveGroupChatRoom,
                              AddChatRoomMember addChatRoomMember,
                              RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple,
                              @Qualifier("GoOutGroupChatRoomService") LeaveGroupChatRoomService leaveGroupChatRoomService,
                              @Qualifier("GoOutLiveGroupChatRoomService") LeaveGroupChatRoomService leaveLiveGroupChatRoomService,
                              LeaveInquiryChatRoomService leaveInquiryChatRoomService,
                              @Qualifier("DeleteInquiryChatRoomService") DeleteChatRoomService deleteInquiryChatRoomService,
                              @Qualifier("DeleteLiveGroupMeetingRoomService") DeleteChatRoomService deleteLiveChatRoomService,
                              CountChatRoomMember countChatRoomMember) {
        this.findLiveGroupChatRoom = findLiveGroupChatRoom;
        this.findGroupChatRoom = findGroupChatRoom;
        this.getInquiryChatHistoryService = getInquiryChatHistoryService;
        this.getGroupChatHistoryService = getGroupChatHistoryService;
        this.retrieveMyChatRoomList = retrieveMyChatRoomList;
        this.findGroupAdminMember = findGroupAdminMember;
        this.findInquiryChatRoomService = findInquiryChatRoomService;
        this.createInquiryChatRoom = createInquiryChatRoom;
        this.createGroupChatRoom = createGroupChatRoom;
        this.createLiveGroupChatRoom = createLiveGroupChatRoom;
        this.addChatRoomMember = addChatRoomMember;
        this.retrieveMemberByAuthPrinciple = retrieveMemberByAuthPrinciple;
        this.leaveGroupChatRoomService = leaveGroupChatRoomService;
        this.leaveLiveGroupChatRoomService = leaveLiveGroupChatRoomService;
        this.leaveInquiryChatRoomService = leaveInquiryChatRoomService;
        this.deleteInquiryChatRoomService = deleteInquiryChatRoomService;
        this.deleteLiveChatRoomService = deleteLiveChatRoomService;
        this.countChatRoomMember = countChatRoomMember;
    }

    @GetMapping("/api/users/chats")
    public ResponseEntity<Object> getMyChatRooms() {

        Object memberId =
                SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getPrincipal();

        Member member  =
                retrieveMemberByAuthPrinciple.
                        getMember(ObjectToLong.convert(memberId));
        List<MyChatRoomDto> myChatRoomDtoList = retrieveMyChatRoomList.get(member);

        return ResponseEntity.
                status(HttpStatus.OK).
                body(myChatRoomDtoList);

    }


        @PostMapping("/api/users/chats/groups/{groupName}/inquiry-room")
    public ResponseEntity<Object> CreateInquireRoom(@PathVariable String groupName){


        Object memberId =
                SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getPrincipal();

        Member requestMember =
                retrieveMemberByAuthPrinciple.
                        getMember(ObjectToLong.convert(memberId));

        Member groupAdminMember =
                findGroupAdminMember.
                        findAdminMember(groupName).
                        getMember();


        Optional<ChatRoom> chatRoom =
                findInquiryChatRoomService.
                        find(groupName, requestMember.getName());

        if(chatRoom.isEmpty()){

            ChatRoom createdChatRoom =
                    createInquiryChatRoom.
                            create(requestMember.getName(),groupName);
            addChatRoomMember.addMember(createdChatRoom,requestMember);
            addChatRoomMember.addMember(createdChatRoom, groupAdminMember);

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body("Chat room is successfully made!");
        }

        return ApiError.
                buildApiError(ErrorCode.CHAT_ROOM_IS_ALREADY_EXISTED,
                HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/api/users/chats/inquiry/{groupName}")
    public ResponseEntity<Object> enterInquiryRoom(@PathVariable String groupName) {

        Object memberId =
                SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getPrincipal();

        Member member =
                retrieveMemberByAuthPrinciple.
                        getMember(ObjectToLong.convert(memberId));

        List<MessageLog> messageLogList =
                getInquiryChatHistoryService.
                        get(member.getName(),groupName);

        return ResponseEntity.
                status(HttpStatus.OK).
                body(messageLogList);

    }

    @PostMapping("/api/users/chats/groups/admins/{groupName}/group-chat")
    public ResponseEntity<Object> CreateGroupChatRoom(@PathVariable String groupName) {

            if(findGroupChatRoom.find(groupName).isEmpty()){

                Object memberId =
                        SecurityContextHolder.
                                getContext().
                                getAuthentication().
                                getPrincipal();

                Member groupAdmin =
                        retrieveMemberByAuthPrinciple.
                                getMember(ObjectToLong.convert(memberId));


                ChatRoom groupChatRoom = createGroupChatRoom.create(groupName);
                addChatRoomMember.addMember(groupChatRoom, groupAdmin);

                return ResponseEntity.
                        status(HttpStatus.OK).
                        body("successfully create the group chat room");
            }


        return ApiError.
                buildApiError(ErrorCode.CHAT_ROOM_IS_ALREADY_EXISTED,
                        HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/users/chats/groups/{groupName}/group-chat")
    public ResponseEntity<Object> enterGroupChat(@PathVariable String groupName) {
        List<MessageLog> messageLogList = getGroupChatHistoryService.get(groupName);

        return ResponseEntity.
                status(HttpStatus.OK).
                body(messageLogList);
    }

    @PostMapping("/api/users/chats/groups/{groupName}/admins/live-chats")
    public ResponseEntity<Object> CreateLiveMeetingChat(@PathVariable String groupName) {

        if (findLiveGroupChatRoom.find(groupName).isEmpty()) {
            Object memberId =
                    SecurityContextHolder.
                            getContext().
                            getAuthentication().
                            getPrincipal();

            Member groupAdmin =
                    retrieveMemberByAuthPrinciple.
                            getMember(ObjectToLong.convert(memberId));

            ChatRoom groupChatRoom = createLiveGroupChatRoom.create(groupName);
            addChatRoomMember.addMember(groupChatRoom, groupAdmin);

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body("successfully create the group chat room");
        }


        return ApiError.
                buildApiError(ErrorCode.CHAT_ROOM_IS_ALREADY_EXISTED,
                        HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/api/users/chats/groups/{groupName}/live-chats")
    public ResponseEntity<Object> enterLiveMeetingChat(@PathVariable String groupName) {
        if(findLiveGroupChatRoom.find(groupName).isEmpty()){
            return ApiError.
                    buildApiError(ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                            HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.
                status(HttpStatus.OK).
                body("enter the chatRoom!");

    }

    @DeleteMapping("/api/users/chats/{groupName}/group-chats")
    public ResponseEntity<Object> exitGroupChatRoom(@PathVariable String groupName) {

        Optional<ChatRoom> chatRoom =findGroupChatRoom.find(groupName);

        if(chatRoom.isPresent()) {
            Object memberId =
                    SecurityContextHolder.
                            getContext().
                            getAuthentication().
                            getPrincipal();

            Member groupUser =
                    retrieveMemberByAuthPrinciple.
                            getMember(ObjectToLong.convert(memberId));

                leaveGroupChatRoomService.goOut(groupName, groupUser);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


        }
                return ApiError.buildApiError(
                        ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                        HttpStatus.BAD_REQUEST);

        }




    @DeleteMapping("/api/users/chats/{groupName}/live-chats")
    public ResponseEntity<Object> exitGroupLiveChatRoom(@PathVariable String groupName) {

        Optional<ChatRoom> chatRoom = findLiveGroupChatRoom.find(groupName);

        if(chatRoom.isPresent()){
            Object memberId =
                    SecurityContextHolder.
                            getContext().
                            getAuthentication().
                            getPrincipal();

            Member groupUser =
                    retrieveMemberByAuthPrinciple.
                            getMember(ObjectToLong.convert(memberId));

            leaveLiveGroupChatRoomService.goOut(groupName, groupUser );
            WebcamUtils.stopSendingVideo();

            if(countChatRoomMember.get(chatRoom.get()) <= 0){
                deleteLiveChatRoomService.delete(chatRoom.get());
            }

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ApiError.buildApiError(
                ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                HttpStatus.BAD_REQUEST);


    }

    @DeleteMapping("/api/users/chats/{groupName}")
    public ResponseEntity<Object> exitInquiryRoom(@PathVariable String groupName) {

        Object memberId =
                SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getPrincipal();

        Member requestMember =
                retrieveMemberByAuthPrinciple.
                        getMember(ObjectToLong.convert(memberId));

        Optional<ChatRoom> chatRoom =
                findInquiryChatRoomService.
                        find(groupName,requestMember.getName());

        if(chatRoom.isPresent()){
            leaveInquiryChatRoomService.goOut(requestMember.getName(), groupName);
            if(countChatRoomMember.get(chatRoom.get()) <= 0 ){
                deleteInquiryChatRoomService.delete(chatRoom.get());
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

        return ApiError.buildApiError(
                ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                HttpStatus.BAD_REQUEST);
    }



}
