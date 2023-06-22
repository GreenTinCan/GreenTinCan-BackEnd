package server.protalktime.gather.domain.application;

import java.util.List;
import java.util.NoSuchElementException;
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

    public Gather findByGatherId(Long gatherId) {
        return gatherRepository.findById(gatherId)
            .orElseThrow(() -> new NoSuchElementException("Member not found by gatherId :" + gatherId));
    }

    @Transactional
    public GatherCreateResponse createGather(Long memberId,GatherCreateRequestDto requestDto) {
        Room room = roomService.createChatRoom(requestDto.getTitle(),false);
        Member member = memberService.findByMemberId(memberId);
        Gather gather = new Gather(requestDto,member,room);
        gatherRepository.save(gather);
        gather.modifyGatherBoardByEnterMember(member);
        return new GatherCreateResponse(room.getId());
    }

    public List<GatherResponseDto> getAllGathersForList(Long memberId,String givenLocation,String givenType) {
        memberService.findByMemberId(memberId);
        List<Gather> gathers = gatherRepository.findAll();

        List<Gather> filteredGathers = gathers.stream()
            .filter(gather -> gather.getLocation().equals(givenLocation)).toList();

        List<Gather> gathersResult = filteredGathers.stream()
            .filter(gather -> gather.getType().equals(givenType)).toList();

        return gathersResult.stream()
            .map(GatherResponseDto::new)
            .collect(Collectors.toList());
    }
    public GatherDetailDto getGatherDetailByGatherId(Long memberId,Long gatherNumber) {
        memberService.findByMemberId(memberId);
        Gather gather = gatherRepository.findById(gatherNumber)
            .orElseThrow(() -> new EntityNotFoundException("Place not found with ID"));
        return new GatherDetailDto(gather);
    }

    public String checkGather(Long memberId,Long gatherId) {
        Gather gather = findByGatherId(gatherId);
        Member member = memberService.findByMemberId(memberId);
        return gather.checkMemberEnter(member);
    }

    public List<GatherResponseDto> getAllGathersBySearchForList(Long memberId,String searchText) {
        Member member = memberService.findByMemberId(memberId);
        List<Gather> gathers = gatherRepository.findAll();
        List<Gather> filteredGathers =gathers.stream()
            .filter(gather -> gather.getScript().contains(searchText))
            .toList();
        return filteredGathers.stream()
            .map(GatherResponseDto::new)
            .collect(Collectors.toList());
    }
}
