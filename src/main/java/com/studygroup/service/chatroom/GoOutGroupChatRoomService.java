package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.entity.RoomMember;
import com.studygroup.enums.RoomType;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.service.groupmember.LeaveGroupChatRoomService;
import com.studygroup.util.ChatRoomNameGenerator;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("GoOutGroupChatRoomService")
@RequiredArgsConstructor
public class GoOutGroupChatRoomService implements LeaveGroupChatRoomService {

    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public void goOut(String groupName, Member member) {
        String groupChatRoomName = ChatRoomNameGenerator.getGroupChatRoomName(groupName);

        ChatRoom chatRoom =
                chatRoomRepository.
                        findByNameAndRoomType(groupChatRoomName,RoomType.GROUP_CHAT).
                        orElseThrow(BindParameterSupplier.
                                        bind(CustomIllegalArgumentException::new,
                                                ErrorCode.CHAT_ROOM_IS_NOT_EXISTED));

            RoomMember roomMember =
                chatRoomMemberRepository.
                        findByMemberAndRoom(member,chatRoom).
                        orElseThrow(
                                BindParameterSupplier.
                                        bind(CustomIllegalArgumentException::new ,
                                                ErrorCode.USER_IS_NOT_IN_CHAT_ROOM));

            chatRoomMemberRepository.delete(roomMember);

    }
}
