package server.protalktime.gather.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import server.protalktime.gather.domain.model.ShortMeet;

@Data
public class ShortMeetDtos {
    @Data
    public static class ShortMeetResponseDto {
        private Long shortMeetId;

        //memberInfo
        private String memberCareer;

        private String type;
        private String title;
        private String location;
        public ShortMeetResponseDto(ShortMeet shortMeet){
            this.shortMeetId = shortMeet.getId();
            this.memberCareer = shortMeet.getMember().getCareer();
            this.type = shortMeet.getType();
            this.title = shortMeet.getTitle();
            this.location = shortMeet.getLocation();
        }
    }
    @Data
    @NoArgsConstructor
    public static class ShortMeetCreateResponse{
        private Long roomId;
        public ShortMeetCreateResponse(Long roomNumber) {
            this.roomId = roomNumber;
        }
    }
    @Data
    public static class ShortMeetCreateRequestDto{

        private String type;
        private String title;
        private String script;
        private String location;

    }
    @Data
    @NoArgsConstructor
    public static class ShortMeetDetailDto{
        private Long shortMeetId;

        //memberInfo
        private Long memberId;
        private String memberNickName;
        private String memberSex;
        private String memberImageUrl;
        private String memberCareer;

        private String type;
        private String title;
        private String script;
        private String location;
        private Long roomId;
        public ShortMeetDetailDto(ShortMeet shortMeet) {
            this.shortMeetId = shortMeet.getId();
            this.memberId = shortMeet.getMember().getId();
            this.memberNickName = shortMeet.getMember().getNickName();
            this.memberSex = shortMeet.getMember().getSex();
            this.memberImageUrl = shortMeet.getMember().getProfileUrl();
            this.memberCareer = shortMeet.getMember().getCareer();
            this.type = shortMeet.getType();
            this.title = shortMeet.getTitle();
            this.script = shortMeet.getScript();
            this.location = shortMeet.getLocation();
            this.roomId = shortMeet.getRoom().getId();
        }
    }
}
