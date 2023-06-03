package com.studygroup.repository;

import com.studygroup.entity.ChatRoom;
import com.studygroup.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository  extends JpaRepository<ChatRoom,Long> {
    public Optional<ChatRoom> findByRoomTypeAndNameContains(RoomType roomType, String groupName);
    public Optional<ChatRoom> findByNameAndRoomType(String roomName, RoomType roomType);
    public Optional<ChatRoom> findByName(String roomName);
    public boolean existsByName(String roomName);

}
