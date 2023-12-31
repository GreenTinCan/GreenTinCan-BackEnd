package server.protalktime.chat.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.protalktime.chat.domain.application.RoomService;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.chat.dto.RoomDtos.*;
import server.protalktime.common.dto.ApiResponse;
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class RoomApiController {
    private final RoomService roomService;
    @GetMapping("/room/all")
    public ApiResponse<List<Room>> room() {
        return ApiResponse.success(roomService.getAllRoom());
    }

    // test
    @PostMapping(value = "/room/test",produces = "application/json;charset=UTF-8")
    public ApiResponse<RoomResponseDto> createRoom(@RequestHeader("Authorization") Long memberId,@RequestParam String name) {
        return ApiResponse.success(new RoomResponseDto(roomService.createRoomByFlag(memberId,name,"GATHER")));
    }

    //list for chat members
    @GetMapping(value = "/room/friends",produces = "application/json;charset=UTF-8")
    public ApiResponse<List<RoomMemberChatListItemResponseDto>> freindsList(@RequestHeader("Authorization") Long memberId) {
        return ApiResponse.success(roomService.findAllFriendsByMemberId(memberId));
    }
    //list for personal RoomList
    @GetMapping(value = "/room",produces = "application/json;charset=UTF-8")
    public ApiResponse<List<RoomsResponseItemDto>> chatRoomPersonalList(@RequestHeader("Authorization") Long memberId) {
        return ApiResponse.success(roomService.findAllByMemberId(memberId));
    }
    @PostMapping(value = "/room/join",produces = "application/json;charset=UTF-8")
    public ApiResponse<String> joinRoom(@RequestHeader("Authorization") Long memberId,@RequestParam Long roomId) {
        return ApiResponse.success(roomService.joinRoom(memberId,roomId));
    }
}
