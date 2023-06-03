package com.studygroup.service.chatroom;

import com.studygroup.entity.*;
import com.studygroup.repository.*;
import com.studygroup.util.ChatRoomNameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindInquiryChatRoomServiceImpl implements FindInquiryChatRoomService {
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    @Override
    public Optional<ChatRoom> find(String inquiryRoomName, Member member) {

         Optional<RoomMember> roomMember =
                 chatRoomMemberRepository.
                         findByMemberAndRoom_Name(member,inquiryRoomName);

         if(roomMember.isPresent()){
             return Optional.ofNullable(roomMember.get().getRoom());
         }

         return Optional.empty();




    }
}
