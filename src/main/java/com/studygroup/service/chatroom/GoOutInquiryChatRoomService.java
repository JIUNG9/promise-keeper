package com.studygroup.service.chatroom;

import com.studygroup.entity.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.util.ChatRoomNameGenerator;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoOutInquiryChatRoomService implements LeaveInquiryChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public void goOut(String userName, String groupName) {
        String inquiryChatRoomName =
                ChatRoomNameGenerator.
                        getInquiryRoomName(userName, groupName);

        ChatRoom chatRoom =chatRoomRepository.
                                findByNameAndRoomType(inquiryChatRoomName, RoomType.INQUIRE_CHAT).
                                orElseThrow(BindParameterSupplier.
                                    bind(CustomIllegalArgumentException::new,
                                        ErrorCode.CHAT_ROOM_IS_NOT_EXISTED));

        chatRoomRepository.delete(chatRoom);

    }
}
