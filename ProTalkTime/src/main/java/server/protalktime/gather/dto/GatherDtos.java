package server.protalktime.gather.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import server.protalktime.gather.domain.model.Gather;

@Data
public class GatherDtos {
    @Data
    public static class GatherResponseDto {
        //memberInfo
        private Long memberId;
        private String memberNickName;
        private String memberSex;
        private String memberImageUrl;
        private String memberCareer;

        //gatherInfo
        private String title;
        private String gatherImageUrl;
        private String type; // 독서 , 스포츠
        private int dNum;
        private int aNum;
        private int lNum;
        private int jNum;
        private int maleNum;
        private int femaleNum;

        public GatherResponseDto(Gather gather) {
            this.memberId = gather.getMember().getId();
            this.memberNickName = gather.getMember().getNickName();
            this.memberSex = gather.getMember().getSex();
            this.memberImageUrl = gather.getMember().getProfileUrl();
            this.memberCareer = gather.getMember().getCareer();

            this.title = gather.getTitle();
            this.gatherImageUrl = gather.getImageUrl();
            this.type = gather.getType();

            this.dNum = gather.getDNum();
            this.aNum = gather.getANum();
            this.lNum = gather.getLNum();
            this.jNum = gather.getJNum();

            this.maleNum = gather.getMaleNum();
            this.femaleNum = gather.getFemaleNum();
        }
    }
    @Data
    @NoArgsConstructor
    public static class GatherCreateResponse{
        private Long roomId;
        public GatherCreateResponse(Long roomNumber) {
            this.roomId =roomNumber;
        }
    }
    @Data
    public static class GatherCreateRequestDto{
        private String title;
        private String script;
        private String imageUrl;
        private String location; // 관악 ,여의도 강남
        private String type; // 독서 , 스포츠

        private Boolean offline;
        private String allowedsex; 
        private int maxNumber;
    }

    @Data
    @NoArgsConstructor
    public static class GatherDetailDto{

        private Long memberId;
        private String memberNickName;
        private String memberSex;
        private String memberImageUrl;
        private String memberCareer;
        private String title;
        private String script;
        private String gatherImageUrl;
        private Boolean offline;
        private String allowedsex; //남성 ,여성, 남성 여성
        private int maxNumber;
        private int dNum;
        private int aNum;
        private int lNum;
        private int jNum;
        private int maleNum;
        private int femaleNum;
        private Long roomId;
        public GatherDetailDto(Gather gather) {
            this.memberId = gather.getMember().getId();
            this.memberNickName = gather.getMember().getNickName();
            this.memberSex = gather.getMember().getSex();
            this.memberImageUrl = gather.getMember().getProfileUrl();
            this.memberCareer = gather.getMember().getCareer();

            this.title = gather.getTitle();
            this.script = gather.getScript();
            this.gatherImageUrl = gather.getImageUrl();

            this.offline = gather.getOffline();
            this.allowedsex = gather.getAllowedsex();
            this.maxNumber = gather.getMaxNumber();

            this.dNum = gather.getDNum();
            this.aNum = gather.getANum();
            this.lNum = gather.getLNum();
            this.jNum = gather.getJNum();
            this.maleNum = gather.getMaleNum();
            this.femaleNum = gather.getFemaleNum();

            this.roomId = gather.getRoom().getId();
        }
    }

}
