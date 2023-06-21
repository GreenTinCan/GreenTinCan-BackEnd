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
import server.protalktime.common.dto.ApiResponse;
import server.protalktime.gather.domain.application.GatherService;
import server.protalktime.gather.dto.GatherDtos.GatherCreateRequestDto;
import server.protalktime.gather.dto.GatherDtos.GatherCreateResponse;
import server.protalktime.gather.dto.GatherDtos.GatherDetailDto;
import server.protalktime.gather.dto.GatherDtos.GatherDetailResponseDto;


@RequiredArgsConstructor
@RestController
@RequestMapping("/gather")
public class GatherApiController {
    private final GatherService gatherService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<GatherCreateResponse> createGather(@RequestBody GatherCreateRequestDto requestDto){
        return ApiResponse.success(gatherService.createGather(requestDto));
    }
    @GetMapping(value = "/all",produces = "application/json;charset=UTF-8")
    public ApiResponse<List<GatherDetailResponseDto>> getGathersForList(@RequestHeader("Authorization") String token,@RequestParam("gatherLocation") String gatherLocation) {
        return ApiResponse.success(gatherService.getAllGathersForList(gatherLocation));
    }
//    @PostMapping(value = "/total", produces = "application/json;charset=UTF-8"
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<GatherDetailDto> getGatherDetail(@RequestParam("gatherNumber") Long gatherNumber, @RequestHeader("Authorization") String token) {
        return ApiResponse.success(gatherService.getGatherDetailByGatherId(gatherNumber));
    }
}
