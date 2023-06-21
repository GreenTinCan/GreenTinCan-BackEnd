package server.protalktime.gather.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import server.protalktime.gather.domain.model.Gather;

public interface GatherRepository extends JpaRepository<Gather,Long> {

}
