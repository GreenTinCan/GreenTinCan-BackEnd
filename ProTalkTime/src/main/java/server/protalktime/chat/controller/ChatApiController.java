package server.protalktime.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import server.protalktime.chat.domain.application.ChatService;
import server.protalktime.chat.domain.model.ChatMessage;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatApiController {

    private final ChatService chatService;
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        chatService.convertAndSend(message);
    }

}
