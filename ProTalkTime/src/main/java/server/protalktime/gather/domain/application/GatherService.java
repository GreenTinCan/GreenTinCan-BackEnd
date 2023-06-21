package server.protalktime.gather.domain.application;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.protalktime.chat.domain.application.RoomService;
import server.protalktime.gather.domain.model.Gather;
import server.protalktime.gather.dto.GatherDtos.GatherCreateRequestDto;
import server.protalktime.gather.dto.GatherDtos.GatherCreateResponse;
import server.protalktime.gather.dto.GatherDtos.GatherDetailDto;
import server.protalktime.gather.dto.GatherDtos.GatherRequestDto;
import server.protalktime.gather.infrastructure.GatherRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GatherService {
    private final GatherRepository gatherRepository;
    private final RoomService roomService;
    @Transactional
    public GatherCreateResponse createGather(GatherCreateRequestDto requestDto) {
        Long roomNumber =  roomService.createGatherRoom(requestDto.getTitle());
    }
    public List<GatherRequestDto> getAllGathersForList() {

    }
    public GatherDetailDto getGatherDetailByGatherId(Long gatherNumber) {
        Gather gather = gatherRepository.findById(gatherNumber)
            .orElseThrow(() -> new EntityNotFoundException("Place not found with ID"));
        return new GatherDetailDto(gather);
    }
}
