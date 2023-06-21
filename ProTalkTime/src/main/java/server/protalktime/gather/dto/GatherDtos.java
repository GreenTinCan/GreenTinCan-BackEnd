package server.protalktime.gather.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import server.protalktime.gather.domain.model.Gather;

@Data
public class GatherDtos {
    @Data
    public static class GatherDetailResponseDto{
        //owner member info - profile image,name,carier,sex
        private Long memberId;
        private String memberNickName;
        private String memberSex;
        private String imageUrl;
        private String carier;
        //title
        //imageUrl
        //남성 숫자, 여성 숫자 ,
        // 각 직군별 숫자
        public GatherDetailResponseDto(Gather gather) {
            this.memberId = gather.getMember().getId();
            //..

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
        private String memberId; // ownerId

        private String title;
        private String script;
        private String location; // 관악 ,여의도 강남
        private String type; // 독서 , 스포츠
        private Boolean offline;
        private String sex; //남성 ,여성, 남성 여성
        private int maxNumber;
        private String imageUrl;

    }

    @Data
    @NoArgsConstructor
    public static class GatherDetailDto{
        //owner member info - profile image,name,carier,sex
        //title
        //imageUrl
        //남성 숫자, 여성 숫자 ,
        // 각 직군별 숫자
        // 채팅방 입장용 번호
        public GatherDetailDto(Gather gather){

        }
    }

}
