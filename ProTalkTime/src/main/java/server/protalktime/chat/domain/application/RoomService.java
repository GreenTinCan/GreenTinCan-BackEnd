package server.protalktime.chat.domain.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.chat.domain.model.RoomMember;
import server.protalktime.chat.dto.RoomDtos.*;
import server.protalktime.chat.infrastructure.RoomMemberRepository;
import server.protalktime.chat.infrastructure.RoomRepository;
import server.protalktime.member.domain.application.MemberService;
import server.protalktime.member.domain.model.Member;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final MemberService memberService;
    @Transactional
    public Room createChatRoom(String name,Boolean oneToOne,String origin) {
        Room room = new Room(name,oneToOne,origin);
        roomRepository.save(room);
        return room;
    }

    @Transactional
    public Room createRoomByFlag(Long memberId , String name ,String origin) {
        Room room = createChatRoom(name,false,origin);
        Member member = memberService.findByMemberId(memberId);
        RoomMember roomMember = new RoomMember(room,member);
        roomMemberRepository.save(roomMember);
        return room;
    }
    @Transactional
    public String joinRoom(Long memberId,Long roomId) {
        if (roomMemberRepository.findByMemberIdAndRoomId(memberId, roomId).isPresent()) {
            log.info("!!!!");
            throw new RuntimeException("Already joined in room");
        }
        Member member = memberService.findByMemberId(memberId);
        Room room = findRoomById(roomId);
        RoomMember roomMember = new RoomMember(room,member);
        roomMemberRepository.save(roomMember);
        return roomMember.getId().toString();
    }

    /**
     * 1 대 1 채팅 또는 랜덤 채팅후 다른 사람과의 채팅을 생성시
     * @param ownerMemberId : 보내고자 하는 사람
     * @param guestMemberId : 받는 사람
     * @param name : 방이름 , 모르면 이름 두개 거삼
     * @return Room 객체 , Room 의 id 값만 필요할 확률 2000%
     */
    @Transactional
    public Room createPrivateRoom(Long ownerMemberId,Long guestMemberId, String name) {
        Room room = createChatRoom(name,true,"ONETOONE");

        Member ownerMember = memberService.findByMemberId(ownerMemberId);
        RoomMember roomOwnerMember = new RoomMember(room,ownerMember);
        roomMemberRepository.save(roomOwnerMember);

        Member guestMember = memberService.findByMemberId(guestMemberId);
        RoomMember roomGuestMember = new RoomMember(room,guestMember);
        roomMemberRepository.save(roomGuestMember);
        return room;
    }
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
    @Transactional
    public void deleteRoom(Long roomId) {
        Room room = findRoomById(roomId);
        roomRepository.delete(room);
    }
    public Room  findRoomById(Long roomId) {
        return roomRepository.findById(roomId)
            .orElseThrow(() -> new EntityNotFoundException("Room not found with ID"));
    }

    public List<RoomMemberChatListItemResponseDto> findAllFriendsByMemberId(Long givenMemberId) {
        memberService.findByMemberId(givenMemberId);
        List<RoomMember> roomMembers = roomMemberRepository.findAllByMemberId(givenMemberId);

        List<Long> roomsId = roomMembers.stream()
            .map(roomMember -> roomMember.getRoom().getId())
            .collect(Collectors.toList());

        Set<Long> memberRoomPartiesIds = new HashSet<>();

        for (Long roomId : roomsId) {
            List<RoomMember> tmp = roomMemberRepository.findAllByRoomId(roomId);
            for (RoomMember roomMember : tmp) {
                memberRoomPartiesIds.add(roomMember.getMember().getId());
            }
        }
        memberRoomPartiesIds.remove(givenMemberId);
        List<Member> result = new ArrayList<>();
        for (Long memberId : memberRoomPartiesIds) {
            result.add(memberService.findByMemberId(memberId));
        }
        return result.stream().map(RoomMemberChatListItemResponseDto::new).toList();
    }
    public List<RoomsResponseItemDto> findAllByMemberId(Long givenMemberId) {
        memberService.findByMemberId(givenMemberId);
        List<RoomMember> roomMembers = roomMemberRepository.findAllByMemberId(givenMemberId);
//        RoomsResponseItemDto(Room room,List<Member> members) {

        List<Room> rooms = roomMembers.stream()
            .map(RoomMember::getRoom)
            .collect(Collectors.toList());

        List<RoomsResponseItemDto> result  = new ArrayList<>();

        for(Room room: rooms) {
            List<RoomMember> singeRoomAndMembers = roomMemberRepository.findAllByRoomId(room.getId());
            List<Member> members = singeRoomAndMembers.stream().map(roomMember -> roomMember.getMember()).toList();
            result.add(new RoomsResponseItemDto(room,members));
        }
        return result;
    }

}
