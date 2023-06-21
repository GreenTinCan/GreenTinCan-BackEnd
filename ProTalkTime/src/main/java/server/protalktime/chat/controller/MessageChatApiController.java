package server.protalktime.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.protalktime.chat.domain.model.ChatMessage;

@RestController
@AllArgsConstructor
public class MessageChatApiController {
    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/chat/send")
    public void sendMessage(@RequestBody ChatMessage message) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);

    }
}
