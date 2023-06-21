package server.protalktime.gather.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import server.protalktime.gather.domain.model.Gather;

@Data
public class GatherDtos {
    @Data
    @NoArgsConstructor
    public static class GatherRequestDto{
        //owner member info - profile image,name,carier,sex
        //title
        //imageUrl
        //남성 숫자, 여성 숫자 ,
        // 각 직군별 숫자

    }
    @Data
    @NoArgsConstructor
    public static class GatherCreateResponse{
        // 채팅방 입장용 번호
    }
    @Data
    public static class GatherCreateRequestDto{
        private String memberId;

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
