package com.studygroup.service.chatroom;

import com.studygroup.dto.MessageLog;
import com.studygroup.entity.ChatRoom;
import com.studygroup.enums.RoomType;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.ChatRoomRepository;
import com.studygroup.util.ChatRoomNameGenerator;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetInquiryChatHistoryServiceImpl implements GetInquiryChatHistoryService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public List<MessageLog> get(String requestMemberName, String groupName) {
        String inquiryRoomName = ChatRoomNameGenerator.getInquiryRoomName(requestMemberName,groupName);

        ChatRoom chatRoom =
                chatRoomRepository.
                        findByNameAndRoomType(inquiryRoomName, RoomType.INQUIRE_CHAT).
                        orElseThrow(BindParameterSupplier.
                                bind(CustomIllegalArgumentException::new,
                                        ErrorCode.CHAT_ROOM_IS_NOT_EXISTED));


        return  chatRoom.
                    getChatList().
                    stream().
                    map(chatList->
                            MessageLog.
                                builder().
                                payload(chatList.getPayload()).
                                sender(chatList.getSender()).
                                build()).
                    collect(Collectors.toList());


    }
}
