package com.studygroup.controller;

import com.studygroup.dto.MessageLog;
import com.studygroup.dto.MyChatRoomDto;
import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.exception.ApiError;
import com.studygroup.service.SendMessageService;
import com.studygroup.service.chat.ExitMessageService;
import com.studygroup.service.chat.GreetingMessageService;
import com.studygroup.service.chatroom.FindGroupChatRoomService;
import com.studygroup.service.chatroom.*;

import com.studygroup.service.groupmember.FindGroupAdminMemberService;
import com.studygroup.service.user.RetrieveMemberByAuthPrinciple;
import com.studygroup.util.ChatRoomNameGenerator;
import com.studygroup.util.WebcamUtils;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.ObjectToLong;
import com.studygroup.util.constant.RoutingKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
public class ChatRoomController {

    private  FindGroupChatRoomService findLiveGroupChatRoom;
    private  FindGroupChatRoomService findGroupChatRoom;
    private GetChatHistoryService getChatHistoryService;
    private  RetrieveMyChatRoomListService retrieveMyChatRoomList;
    private  FindGroupAdminMemberService findGroupAdminMember;
    private  FindInquiryChatRoomService findInquiryChatRoomService;
    private  CreateOneToOneChatRoomService createInquiryChatRoom;
    private  CreateChatRoomService createGroupChatRoom;
    private  CreateChatRoomService createLiveGroupChatRoom;
    private  AddChatRoomMember addChatRoomMember;
    private  RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple;
    private LeaveChatRoomService leaveChatRoomService;
    private  DeleteChatRoomService deleteInquiryChatRoomService;
    private  DeleteChatRoomService deleteLiveChatRoomService;
    private CountChatRoomMemberService countChatRoomMemberService;
    private FindMemberIsInChatRoom findMemberIsInChatRoom;
    private IsOneToOneChatRoomExistService isOneToOneChatRoomExistService;
    private GetGroupNickNameService getGroupNickNameService;
    private SendMessageService sendMessageService;
    private GreetingMessageService greetingMessageService;
    private ExitMessageService exitMessageService;


    public ChatRoomController(@Qualifier("FindLiveGroupChatRoomService") FindGroupChatRoomService findLiveGroupChatRoom,
                              @Qualifier("FindGroupChatRoomServiceImpl") FindGroupChatRoomService findGroupChatRoom,
                              GetChatHistoryService getChatHistoryService,
                              @Qualifier("RetrieveMyChatRoomListServiceImpl") RetrieveMyChatRoomListService retrieveMyChatRoomList,
                              @Qualifier("FindGroupAdminMemberServiceImpl") FindGroupAdminMemberService findGroupAdminMember,
                              FindInquiryChatRoomService findInquiryChatRoomService,
                              @Qualifier("CreateInquiryChatRoomService") CreateOneToOneChatRoomService createInquiryChatRoom,
                              @Qualifier("CreateGroupChatRoomService") CreateChatRoomService createGroupChatRoom,
                              @Qualifier("CreateLiveChatRoomService") CreateChatRoomService createLiveGroupChatRoom,
                              AddChatRoomMember addChatRoomMember,
                              RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple,
                              @Qualifier("DeleteInquiryChatRoomService") DeleteChatRoomService deleteInquiryChatRoomService,
                              @Qualifier("DeleteLiveGroupMeetingRoomService") DeleteChatRoomService deleteLiveChatRoomService,
                              CountChatRoomMemberService countChatRoomMemberService,
                              FindMemberIsInChatRoom findMemberIsInChatRoom,
                              LeaveChatRoomService leaveChatRoomService,
                              IsOneToOneChatRoomExistService isOneToOneChatRoomExistService,
                              GetGroupNickNameService getGroupNickNameService,
                              SendMessageService sendMessageService,
                              GreetingMessageService greetingMessageService,
                              ExitMessageService exitMessageService

                              ) {
        this.findMemberIsInChatRoom = findMemberIsInChatRoom;
        this.leaveChatRoomService = leaveChatRoomService;
        this.findLiveGroupChatRoom = findLiveGroupChatRoom;
        this.findGroupChatRoom = findGroupChatRoom;
        this.getChatHistoryService = getChatHistoryService;
        this.retrieveMyChatRoomList = retrieveMyChatRoomList;
        this.findGroupAdminMember = findGroupAdminMember;
        this.findInquiryChatRoomService = findInquiryChatRoomService;
        this.createInquiryChatRoom = createInquiryChatRoom;
        this.createGroupChatRoom = createGroupChatRoom;
        this.createLiveGroupChatRoom = createLiveGroupChatRoom;
        this.addChatRoomMember = addChatRoomMember;
        this.retrieveMemberByAuthPrinciple = retrieveMemberByAuthPrinciple;
        this.deleteInquiryChatRoomService = deleteInquiryChatRoomService;
        this.deleteLiveChatRoomService = deleteLiveChatRoomService;
        this.countChatRoomMemberService = countChatRoomMemberService;
        this.isOneToOneChatRoomExistService = isOneToOneChatRoomExistService;
        this.getGroupNickNameService = getGroupNickNameService;
        this.sendMessageService = sendMessageService;
        this.exitMessageService = exitMessageService;
        this.greetingMessageService = greetingMessageService;
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

        if(myChatRoomDtoList.size()<=0){
            return ResponseEntity.
                    status(HttpStatus.NO_CONTENT).
                    build();
        }

        return ResponseEntity.
                status(HttpStatus.OK).
                body(myChatRoomDtoList);

    }



        @PostMapping("/api/users/chats/{groupName}/inquiry-room")
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

            String roomName =
                    ChatRoomNameGenerator.
                            getInquiryRoomName(requestMember.getName(),
                                    groupName);

            Optional<ChatRoom> chatRoom =
                findInquiryChatRoomService.
                        find(roomName, requestMember);

        if(chatRoom.isPresent()){
            return ApiError.
                    buildApiError(ErrorCode.CHAT_ROOM_IS_ALREADY_EXISTED,
                            HttpStatus.BAD_REQUEST);
        }

            ChatRoom createdChatRoom =
                    createInquiryChatRoom.
                            create(requestMember.getName(), groupName);
            addChatRoomMember.addMember(createdChatRoom, requestMember);
            addChatRoomMember.addMember(createdChatRoom, groupAdminMember);
            sendMessageService.sendMessage(
                    RoutingKeyGenerator.
                            inquiryRoutingKey(createdChatRoom.getName()),
                                    greetingMessageService.get(requestMember.getName(), createdChatRoom));
            sendMessageService.sendMessage(
                    RoutingKeyGenerator.
                            inquiryRoutingKey(createdChatRoom.getName()),
                    greetingMessageService.get(groupName+" 관리자", createdChatRoom));

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body("Chat room is successfully created!");

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

        String roomName = ChatRoomNameGenerator.getInquiryRoomName(member.getName(),groupName);

        if (isOneToOneChatRoomExistService.isExist(roomName)) {

            Optional<ChatRoom> chatRoom =
                    findInquiryChatRoomService.
                            find(roomName, member);

            if (chatRoom.isPresent()) {
                    List<MessageLog> messageLogList =
                            getChatHistoryService.
                                    get(member, chatRoom.get());

                    if (messageLogList.size() <= 0) {
                        return ResponseEntity.
                                status(HttpStatus.NO_CONTENT).
                                body(messageLogList);
                    }
                    return ResponseEntity.
                            status(HttpStatus.OK).
                            body(messageLogList);


            }
            return ApiError.
                    buildApiError(
                            ErrorCode.USER_IS_NOT_IN_CHAT_ROOM,
                            HttpStatus.BAD_REQUEST);

        }
        return ApiError.
                buildApiError(
                        ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                        HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/users/chats/groups/{groupName}/admins/group-chat")
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
                String roomName = ChatRoomNameGenerator.getGroupChatRoomName(groupName);
                String routingKey = RoutingKeyGenerator.groupChatRoomRoutingKey(roomName);
                String memberNickName = getGroupNickNameService.getNickName(groupAdmin, groupName);
                sendMessageService.sendMessage(
                        routingKey,
                        greetingMessageService.get(memberNickName, groupChatRoom));

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

        Optional<ChatRoom> chatRoom = findGroupChatRoom.find(groupName);
        if(chatRoom.isPresent()){

            Object memberId =
                    SecurityContextHolder.
                            getContext().
                            getAuthentication().
                            getPrincipal();

            Member groupMember =
                    retrieveMemberByAuthPrinciple.
                            getMember(ObjectToLong.convert(memberId));

            if(findMemberIsInChatRoom.find(groupMember,chatRoom.get().getName()).isEmpty()){
                String roomName = ChatRoomNameGenerator.getGroupChatRoomName(groupName);
                String routingKey = RoutingKeyGenerator.groupChatRoomRoutingKey(roomName);
                String memberNickName = getGroupNickNameService.getNickName(groupMember, groupName);
                addChatRoomMember.addMember(chatRoom.get(), groupMember);
                sendMessageService.sendMessage(
                        routingKey,
                        greetingMessageService.get(memberNickName,chatRoom.get()));
                    }

            List<MessageLog> messageLogList =
                    getChatHistoryService.
                            get(groupMember, chatRoom.get());

            if(messageLogList.size() <= 0 ){
                return ResponseEntity.
                        status(HttpStatus.NO_CONTENT).
                        build();
            }

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(messageLogList);

        }
        return ApiError.buildApiError(
                ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                HttpStatus.BAD_REQUEST);

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

            ChatRoom groupLiveChatRoom = createLiveGroupChatRoom.create(groupName);
            addChatRoomMember.addMember(groupLiveChatRoom, groupAdmin);
            String roomName = ChatRoomNameGenerator.getLiveChatRoomName(groupName);
            String routingKey = RoutingKeyGenerator.liveGroupChatRoutingKey(roomName);
            String memberNickName = getGroupNickNameService.getNickName(groupAdmin, groupName);
            sendMessageService.sendMessage(
                    routingKey,
                    greetingMessageService.get(memberNickName, groupLiveChatRoom));

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

        Optional<ChatRoom> chatRoom = findLiveGroupChatRoom.find(groupName);

        if(chatRoom.isPresent()){

            Object memberId =
                    SecurityContextHolder.
                            getContext().
                            getAuthentication().
                            getPrincipal();

            Member groupMember =
                    retrieveMemberByAuthPrinciple.
                            getMember(ObjectToLong.convert(memberId));

            if(findMemberIsInChatRoom.find(groupMember,chatRoom.get().getName()).isEmpty()) {
                String roomName = ChatRoomNameGenerator.getLiveChatRoomName(groupName);
                String routingKey = RoutingKeyGenerator.liveGroupChatRoutingKey(roomName);
                String memberNickName = getGroupNickNameService.getNickName(groupMember, groupName);
                addChatRoomMember.addMember(chatRoom.get(), groupMember);
                sendMessageService.sendMessage(
                        routingKey,
                        greetingMessageService.get(memberNickName, chatRoom.get()));
            }

            List<MessageLog> messageLogList =
                    getChatHistoryService.
                            get(groupMember, chatRoom.get());

            if(messageLogList.size() <=0 ){
                return  ResponseEntity.
                        status(HttpStatus.NO_CONTENT).
                        body(messageLogList);
            }
            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(messageLogList);
        }
            return ApiError.
                    buildApiError(ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                            HttpStatus.BAD_REQUEST);
        }



    @DeleteMapping("/api/users/chats/groups/{groupName}/group-chats")
    public ResponseEntity<Object> exitGroupChatRoom(@PathVariable String groupName) {

        Optional<ChatRoom> chatRoom =findGroupChatRoom.find(groupName);

        if(chatRoom.isPresent()) {
            Object memberId =
                    SecurityContextHolder.
                            getContext().
                            getAuthentication().
                            getPrincipal();

            Member groupMember =
                    retrieveMemberByAuthPrinciple.
                            getMember(ObjectToLong.convert(memberId));

            leaveChatRoomService.goOut(groupMember, chatRoom.get());
            String roomName = ChatRoomNameGenerator.getGroupChatRoomName(groupName);
            String routingKey = RoutingKeyGenerator.groupChatRoomRoutingKey(roomName);
            String memberNickName = getGroupNickNameService.getNickName(groupMember, groupName);
            sendMessageService.sendMessage(
                    routingKey,
                    exitMessageService.get(memberNickName, chatRoom.get()));

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


        }
                return ApiError.buildApiError(
                        ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                        HttpStatus.BAD_REQUEST);

        }


    @DeleteMapping("/api/users/chats/groups/{groupName}/live-chats")
    public ResponseEntity<Object> exitGroupLiveChatRoom(@PathVariable String groupName) {

        Optional<ChatRoom> chatRoom = findLiveGroupChatRoom.find(groupName);

        if(chatRoom.isPresent()){
            Object memberId =
                    SecurityContextHolder.
                            getContext().
                            getAuthentication().
                            getPrincipal();

            Member groupMember =
                    retrieveMemberByAuthPrinciple.
                            getMember(ObjectToLong.convert(memberId));

            leaveChatRoomService.goOut(groupMember,chatRoom.get());
            String roomName = ChatRoomNameGenerator.getLiveChatRoomName(groupName);
            String routingKey = RoutingKeyGenerator.liveGroupChatRoutingKey(roomName);
            String memberNickName = getGroupNickNameService.getNickName(groupMember, groupName);
            sendMessageService.sendMessage(
                    routingKey,
                    exitMessageService.get(memberNickName, chatRoom.get()));
            WebcamUtils.stopSendingVideo();

            if(countChatRoomMemberService.get(chatRoom.get()) <= 0){
                deleteLiveChatRoomService.delete(chatRoom.get());
                return ResponseEntity.
                        status(HttpStatus.OK).
                        body("Live chat session will be terminated");

            }

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ApiError.buildApiError(
                ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                HttpStatus.BAD_REQUEST);


    }

    @DeleteMapping("/api/users/chats/{roomName}")
    public ResponseEntity<Object> exitInquiryRoom(@PathVariable String roomName) {

        Object memberId =
                SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getPrincipal();

        Member requestMember =
                retrieveMemberByAuthPrinciple.
                        getMember(ObjectToLong.convert(memberId));

        Optional<ChatRoom> userInChatRoom =
                findInquiryChatRoomService.
                        find(roomName,requestMember);

        if(isOneToOneChatRoomExistService.isExist(roomName)){
            if (userInChatRoom.isPresent()) {
                leaveChatRoomService.goOut(requestMember, userInChatRoom.get());

                String routingKey =
                        RoutingKeyGenerator.
                                inquiryRoutingKey(roomName);
                sendMessageService.sendMessage(
                        routingKey,
                        exitMessageService.get(requestMember.getName(), userInChatRoom.get()));

                if (countChatRoomMemberService.get(userInChatRoom.get()) <= 0) {
                    deleteInquiryChatRoomService.delete(userInChatRoom.get());
                }

                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ApiError.buildApiError(
                    ErrorCode.USER_IS_NOT_IN_CHAT_ROOM,
                    HttpStatus.BAD_REQUEST);
        }

        return ApiError.buildApiError(
                ErrorCode.CHAT_ROOM_IS_NOT_EXISTED,
                HttpStatus.BAD_REQUEST);
    }



}
