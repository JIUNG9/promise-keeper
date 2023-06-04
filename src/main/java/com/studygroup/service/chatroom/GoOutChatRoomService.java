package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.entity.RoomMember;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoOutChatRoomService implements LeaveChatRoomService {
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    @Override
    public void goOut(Member member, ChatRoom chatRoom) {

        RoomMember roomMember = chatRoomMemberRepository.
                findByMemberAndRoom(member, chatRoom).
                orElseThrow(BindParameterSupplier.
                                bind(CustomIllegalArgumentException::new,
                                        ErrorCode.USER_IS_NOT_IN_CHAT_ROOM ));

        chatRoomMemberRepository.delete(roomMember);



    }
}
