package server.protalktime.chat.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.protalktime.chat.domain.model.ChatEntity;
@Repository
public interface ChatEntityRepository extends JpaRepository<ChatEntity,Long> {

}
