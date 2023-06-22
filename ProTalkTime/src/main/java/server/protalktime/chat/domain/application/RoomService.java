package server.protalktime.chat.domain.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
    public void joinChatRoom(Room room , Member member) {
        RoomMember roomMember = new RoomMember(room,member);
        roomMemberRepository.save(roomMember);
    }
    public Room createGatherRoom(Long memberId , String name) {
        Room room = createChatRoom(name,false,"GATHER");
        joinChatRoom(room,memberService.findByMemberId(memberId));
        return room;
    }
    public Room createOntToOneRoom(String name) {
        return createChatRoom(name,true,"OTO");
    }
    public Room createLightningRoom(String name) {
        return createChatRoom(name,false,"LIGHTNING");
    }
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
    public Room  findRoomById(Long roomId) {
        return roomRepository.findById(roomId)
            .orElseThrow(() -> new EntityNotFoundException("Room not found with ID"));
    }
    @Transactional
    public void deleteRoom(Long roomId) {
        Room room = findRoomById(roomId);
        roomRepository.delete(room);
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
