package server.protalktime.gather.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.protalktime.gather.domain.model.ShortMeet;

@Repository
public interface ShortMeetRepository extends JpaRepository<ShortMeet,Long> {

}
