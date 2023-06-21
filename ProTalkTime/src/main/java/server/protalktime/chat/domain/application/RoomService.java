package server.protalktime.chat.domain.application;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.chat.infrastructure.RoomRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    @Transactional
    public Room createChatRoom(String name) {
        Room room = new Room();
        roomRepository.save(room);
        return room;
    }

    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
    public Room  findRoomById(Long roomId) {
        return roomRepository.findById(roomId)
            .orElseThrow(() -> new EntityNotFoundException("Room not found with ID"));
    }
    @Transactional
    public void deleteRoom(Long roomId) {
        Room room = findRoomById(roomId);
        roomRepository.delete(room);
    }
}
