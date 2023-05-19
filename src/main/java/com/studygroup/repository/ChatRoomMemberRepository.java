package com.studygroup.repository;

import com.studygroup.entity.RoomMember;
import com.studygroup.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<RoomMember, Integer> {
    RoomMember findByMember_IdAndRoom_Id_AndRoomType(Long memberId, UUID roomId, RoomType roomType);

}
