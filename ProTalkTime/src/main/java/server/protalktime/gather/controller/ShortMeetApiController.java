package server.protalktime.gather.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.protalktime.gather.domain.application.ShortMeetService;
import server.protalktime.common.dto.ApiResponse;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetCreateRequestDto;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetCreateResponse;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetDetailDto;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetResponseDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/short/meet")
public class ShortMeetApiController {

    private final ShortMeetService shortMeetService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<ShortMeetCreateResponse> createShortMeet(@RequestHeader("Authorization") Long memberId,@RequestBody ShortMeetCreateRequestDto requestDto){
        return ApiResponse.success(shortMeetService.createShortMeet(memberId,requestDto));
    }
    @GetMapping(value = "/all",produces = "application/json;charset=UTF-8")
    public ApiResponse<List<ShortMeetResponseDto>> getShortMeetsForList(@RequestHeader("Authorization") Long memberId) {
        return ApiResponse.success(shortMeetService.getAllShortMeetsForList(memberId));
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<ShortMeetDetailDto> getShortMeetDetail(@RequestHeader("Authorization") Long memberId,@RequestParam("shorMeetId") Long shorMeetId) {
        return ApiResponse.success(shortMeetService.getShortMeetDetailByShortMeetId(memberId,shorMeetId));
    }

//    @GetMapping(value = "/join",produces = "application/json;charset=UTF-8")
//    public ApiResponse<String> joinShortMeetCheck(@RequestHeader("Authorization") Long memberId, @RequestParam("shorMeetId") Long shorMeetId){
//        return ApiResponse.success(shortMeetService.checkShortMeet(memberId,shorMeetId));
//    }

}

