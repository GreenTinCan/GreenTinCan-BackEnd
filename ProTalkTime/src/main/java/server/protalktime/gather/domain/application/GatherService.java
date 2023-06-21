package server.protalktime.gather.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.protalktime.gather.infrastructure.GatherRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GatherService {
    private final GatherRepository gatherRepository;


}
