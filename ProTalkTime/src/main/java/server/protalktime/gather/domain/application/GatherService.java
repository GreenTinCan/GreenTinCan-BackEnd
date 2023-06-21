package server.protalktime.gather.domain.application;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.protalktime.chat.domain.application.RoomService;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.gather.domain.model.Gather;
import server.protalktime.gather.dto.GatherDtos.GatherCreateRequestDto;
import server.protalktime.gather.dto.GatherDtos.GatherCreateResponse;
import server.protalktime.gather.dto.GatherDtos.GatherDetailDto;
import server.protalktime.gather.dto.GatherDtos.GatherDetailResponseDto;
import server.protalktime.gather.infrastructure.GatherRepository;
import server.protalktime.member.domain.application.MemberService;
import server.protalktime.member.domain.model.Member;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GatherService {
    private final GatherRepository gatherRepository;
    private final RoomService roomService;
    private final MemberService memberService;
    @Transactional
    public GatherCreateResponse createGather(GatherCreateRequestDto requestDto) {
        Room room = roomService.createChatRoom(requestDto.getTitle(),false);
        Member member = memberService.findByMemberId(requestDto.getMemberId());
        Gather gather = new Gather(requestDto,member,room);
        gatherRepository.save(gather);
        return new GatherCreateResponse(room.getId());
    }

    public List<GatherDetailResponseDto> getAllGathersForList(String location) {
        List<Gather> gathers = gatherRepository.findAll();

        return gathers.stream().map(GatherDetailResponseDto::new).collect(Collectors.toList());
    }
    public GatherDetailDto getGatherDetailByGatherId(Long gatherNumber) {
        Gather gather = gatherRepository.findById(gatherNumber)
            .orElseThrow(() -> new EntityNotFoundException("Place not found with ID"));
        return new GatherDetailDto(gather);
    }
}
