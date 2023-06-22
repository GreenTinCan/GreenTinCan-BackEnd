package server.protalktime.gather.domain.application;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.protalktime.chat.domain.application.RoomService;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.gather.domain.model.ShortMeet;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetCreateRequestDto;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetCreateResponse;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetDetailDto;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetResponseDto;
import server.protalktime.gather.infrastructure.ShortMeetRepository;
import server.protalktime.member.domain.application.MemberService;
import server.protalktime.member.domain.model.Member;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ShortMeetService {
    private final ShortMeetRepository shortMeetRepository;
    private final RoomService roomService;
    private final MemberService memberService;

    public ShortMeet findByShortMeetId(Long shortMeetId) {
        return shortMeetRepository.findById(shortMeetId)
            .orElseThrow(() -> new EntityNotFoundException("ShortMeet not found by shortMeetId :" + shortMeetId));
    }

    @Transactional
    public ShortMeetCreateResponse createShortMeet(Long memberId, ShortMeetCreateRequestDto requestDto) {
        Room room = roomService.createRoomByFlag(memberId,requestDto.getTitle(),requestDto.getType());
        Member member = memberService.findByMemberId(memberId);
        ShortMeet shortMeet = new ShortMeet(requestDto,member,room);
        shortMeetRepository.save(shortMeet);
        return new ShortMeetCreateResponse(room.getId());
    }

    public List<ShortMeetResponseDto> getAllShortMeetsForList(Long memberId) {
        memberService.findByMemberId(memberId);
        List<ShortMeet> shortMeets = shortMeetRepository.findAll();
        return shortMeets.stream().map(ShortMeetResponseDto::new).collect(Collectors.toList());
    }
    public ShortMeetDetailDto getShortMeetDetailByShortMeetId(Long memberId,Long shorMeetId) {
        memberService.findByMemberId(memberId);
        return new ShortMeetDetailDto(findByShortMeetId(shorMeetId));
    }

}



