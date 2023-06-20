package server.protalktime.infra.aws.testconnect.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.protalktime.common.dto.ApiResponse;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class ConnectionCheckApi {
    @GetMapping(value="/{testnumber}")
    public ApiResponse getConnectTest(@PathVariable Long testNumber){
        return ApiResponse.success(testNumber);
    }
}
