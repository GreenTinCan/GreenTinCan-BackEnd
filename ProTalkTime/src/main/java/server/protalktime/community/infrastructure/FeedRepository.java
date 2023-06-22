package server.protalktime.community.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.protalktime.community.domain.model.Feed;
@Repository
public interface FeedRepository extends JpaRepository<Feed,Long> {

}
