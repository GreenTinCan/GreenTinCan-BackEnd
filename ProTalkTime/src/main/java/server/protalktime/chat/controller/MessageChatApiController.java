package server.protalktime.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.protalktime.chat.domain.application.ChatService;
import server.protalktime.chat.domain.model.ChatMessage;
import server.protalktime.common.dto.ApiResponse;

@RestController
@RequiredArgsConstructor
public class MessageChatApiController {
    private final ChatService chatService;
    @PostMapping("/chat/send")
    public ApiResponse sendMessage(@RequestBody ChatMessage message) {
        chatService.convertAndSend(message);
        return ApiResponse.success(message.getMessage());
    }
}
