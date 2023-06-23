package server.protalktime.chat.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.protalktime.chat.domain.model.ChatEntity;
import server.protalktime.chat.domain.model.ChatEntity.MessageType;


@Data
public class ChatDtos {
    @Data
    public static class ChatResponseDto {
        private Long roomId;

        private Long chatMessageId;
        private MessageType type;
        private String message;
        private LocalDateTime messageTime;

        private Long memberId;
        private String memberNickName;
        private String memberSex;
        private String memberImageUrl;
        private String memberCareer;
        public ChatResponseDto(ChatEntity chatEntity) {
            this.roomId = chatEntity.getRoom().getId();
            this.chatMessageId = chatEntity.getId();
            this.type = chatEntity.getType();
            this.message = chatEntity.getMessage();
            this.messageTime =chatEntity.getCreatedDateTime();

            this.memberId = chatEntity.getMember().getId();
            this.memberNickName = chatEntity.getMember().getNickName();
            this.memberSex = chatEntity.getMember().getSex();
            this.memberImageUrl = chatEntity.getMember().getProfileUrl();
            this.memberCareer = chatEntity.getMember().getCareer();
        }
    }
    @Data
    @NoArgsConstructor
    public static class ChatRequestDto {
        private MessageType type;
        private String message;
    }

}
