package com.studygroup.service.groupmember;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.entity.RoomMember;
import com.studygroup.enums.RoomType;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.util.ChatRoomNameGenerator;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("GoOutLiveGroupChatRoomService")
@RequiredArgsConstructor
public class GoOutLiveGroupChatRoomService implements LeaveGroupChatRoomService {

    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void goOut(String groupName, Member member) {
        String liveGroupChatRoomName = ChatRoomNameGenerator.getLiveChatRoomName(groupName);

        ChatRoom chatRoom =
                chatRoomRepository.
                        findByNameAndRoomType(liveGroupChatRoomName, RoomType.LIVE_GROUP_CHAT).
                        orElseThrow(BindParameterSupplier.
                                bind(CustomIllegalArgumentException::new,
                                        ErrorCode.CHAT_ROOM_IS_NOT_EXISTED));
        RoomMember roomMember =
                chatRoomMemberRepository.
                        findByMemberAndRoom(member, chatRoom).
                        orElseThrow(
                                BindParameterSupplier.
                                        bind(CustomIllegalArgumentException::new,
                                                ErrorCode.USER_IS_NOT_IN_CHAT_ROOM));

        chatRoomMemberRepository.delete(roomMember);
    }
}
