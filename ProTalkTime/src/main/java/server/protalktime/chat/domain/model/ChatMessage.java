package server.protalktime.chat.domain.model;

import lombok.Data;

@Data
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK,LEAVE
    }

    private MessageType type; // 메시지 타입
    private Long roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}
