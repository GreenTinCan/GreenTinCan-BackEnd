package server.protalktime.chat.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.protalktime.chat.domain.model.RoomMember;

@Repository
public interface RoomMemberRepository extends JpaRepository<RoomMember,Long> {
    List<RoomMember> findAllByMemberId(Long memberId);
    List<RoomMember> findAllByRoomId(Long roomId);
}
