package server.protalktime.chat.infrastructure;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.protalktime.chat.domain.model.RoomMember;

@Repository
public interface RoomMemberRepository extends JpaRepository<RoomMember,Long> {
    List<RoomMember> findAllByMemberId(Long memberId);
    List<RoomMember> findAllByRoomId(Long roomId);
    @Query("SELECT rm FROM RoomMember rm WHERE rm.member.id = :memberId AND rm.room.id = :roomId")
    Optional<RoomMember> findByMemberIdAndRoomId(@Param("memberId") Long memberId, @Param("roomId") Long roomId);
}
