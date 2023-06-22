package server.protalktime.chat.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.member.domain.model.Member;

@Data
public class RoomDtos {
    @Data
    public static class RoomResponseDto {
        private Long roomId;
        private String name;
        private String origin;
        public RoomResponseDto(Room room) {
                this.roomId = room.getId();
                this.name = room.getName();
                this.origin = room.getOrigin();
        }
    }
    @Data
    public static class RoomsResponseItemDto {
        private Long roomId;
        private String name;
        private String origin;
        private List<RoomMemberChatListItemResponseDto> roomMembers;
        public RoomsResponseItemDto(Room room,List<Member> members) {
            this.roomId = room.getId();
            this.name = room.getName();
            this.origin = room.getOrigin();
            this.roomMembers= members.
                stream().map(RoomMemberChatListItemResponseDto::new).
                collect(Collectors.toList());
        }
    }
    @Data
    public static class RoomMemberChatListItemResponseDto {
        private Long memberId;
        private String nickName;
        private String profileUrl;
        public RoomMemberChatListItemResponseDto(Member member) {
            this.memberId = member.getId();
            this.nickName = member.getNickName();
            this.profileUrl = member.getProfileUrl();
        }
    }
}
