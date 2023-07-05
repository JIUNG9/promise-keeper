package com.studygroup.repository;

import com.studygroup.domain.ChatRoom;
import com.studygroup.domain.Member;
import com.studygroup.domain.RoomMember;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<RoomMember, Long> {

  List<RoomMember> findByMember(Member member);

  Optional<RoomMember> findByMemberAndRoom(Member member, ChatRoom chatRoom);

  Optional<RoomMember> findByMemberAndRoom_Name(Member member, String roomName);

  int countByRoom(ChatRoom chatRoom);


}
