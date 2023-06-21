package server.protalktime.chat.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.protalktime.chat.domain.application.RoomService;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.common.dto.ApiResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class RoomApiController {
    private final RoomService roomService;
    @GetMapping("/room/all")
    public ApiResponse<List<Room>> room() {
        return ApiResponse.success(roomService.getAllRoom());
    }

    @PostMapping("/room")
    public ApiResponse<Room> createRoom(@RequestParam String name) {
        return ApiResponse.success(roomService.createChatRoom(name,true));
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ApiResponse<Room> roomInfo(@PathVariable Long roomId) {
        return ApiResponse.success(roomService.findRoomById(roomId));
    }
}
