package server.protalktime.chat.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import server.protalktime.chat.domain.model.ChatEntity;
import server.protalktime.chat.domain.model.ChatMessage;
import server.protalktime.chat.dto.ChatDtos.*;
import server.protalktime.chat.infrastructure.ChatEntityRepository;
import server.protalktime.member.domain.application.MemberService;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final SimpMessageSendingOperations messagingTemplate;
    private final RoomService roomService;
    private final MemberService memberService;

    public void convertAndSend(ChatMessage message) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    // sector for hackaton only
    private final ChatEntityRepository chatEntityRepository;
    public ChatResponseDto sendAndSaveMessage(Long memberId ,Long roomId,ChatRequestDto requestDto) {
        memberService.findByMemberId(memberId);
        roomService.findRoomById(roomId);
        ChatEntity chatEntity = new ChatEntity(
            memberService.findByMemberId(memberId),
            roomService.findRoomById(roomId),
            requestDto);
        chatEntityRepository.save(chatEntity);
        return new ChatResponseDto(chatEntity);
    }


}
