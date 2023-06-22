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
import server.protalktime.gather.dto.GatherDtos.GatherResponseDto;
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
    public GatherCreateResponse createGather(Long memberId,GatherCreateRequestDto requestDto) {
        Room room = roomService.createChatRoom(requestDto.getTitle(),false);
        Member member = memberService.findByMemberId(memberId);
        Gather gather = new Gather(requestDto,member,room);
        gatherRepository.save(gather);
        gather.modifyGatherBoardByEnterMember(member);
        return new GatherCreateResponse(room.getId());
    }

    public List<GatherResponseDto> getAllGathersForList(String givenLocation) {
        List<Gather> gathers = gatherRepository.findAll();
        List<Gather> filteredGathers = gathers.stream()
            .filter(gather -> gather.getLocation().equals(givenLocation)).toList();
        return filteredGathers.stream()
            .map(GatherResponseDto::new)
            .collect(Collectors.toList());
    }
    public GatherDetailDto getGatherDetailByGatherId(Long gatherNumber) {
        Gather gather = gatherRepository.findById(gatherNumber)
            .orElseThrow(() -> new EntityNotFoundException("Place not found with ID"));
        return new GatherDetailDto(gather);
    }
}
