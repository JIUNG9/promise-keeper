package com.studygroup.service.chatroom;

import com.studygroup.enums.RoomType;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service("CheckUserIsInLiveMeetingRoomService")
public class CheckUserIsInLiveMeetingRoomService implements CheckUserIsInChatRoomService {

    private final ChatRoomMemberRepository chatRoomMemberRepository;
    @Override
    public boolean check(Long memberID, UUID roomId) {
        return Optional.
                ofNullable(chatRoomMemberRepository.
                        findByMember_IdAndRoom_Id_AndRoomType(
                                memberID,
                                roomId,
                                RoomType.LIVE_GROUP_CHAT)).
                isPresent();


    }
}
