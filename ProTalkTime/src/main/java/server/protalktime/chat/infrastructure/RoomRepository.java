package server.protalktime.chat.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.protalktime.chat.domain.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

}
