package com.studygroup.repository;

import com.studygroup.domain.ChatRoom;
import com.studygroup.enums.RoomType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

  Optional<ChatRoom> findByRoomTypeAndNameContains(RoomType roomType, String groupName);

  Optional<ChatRoom> findByNameAndRoomType(String roomName, RoomType roomType);

  Optional<ChatRoom> findByName(String roomName);

  boolean existsByName(String roomName);

}
