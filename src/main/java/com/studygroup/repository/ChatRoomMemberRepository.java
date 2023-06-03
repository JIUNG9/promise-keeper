package com.studygroup.repository;

import com.studygroup.entity.ChatRoom;
import com.studygroup.entity.Member;
import com.studygroup.entity.RoomMember;
import com.studygroup.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<RoomMember, Long> {
    List<RoomMember> findByMember(Member member);
    Optional<RoomMember> findByMemberAndRoom(Member member, ChatRoom chatRoom);
    Optional<RoomMember> findByMemberAndRoom_Name(Member member, String roomName);
    int countByRoom(ChatRoom chatRoom);


}
