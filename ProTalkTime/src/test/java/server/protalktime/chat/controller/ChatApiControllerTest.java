package server.protalktime.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import server.protalktime.chat.domain.model.ChatMessage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(ChatApiController.class)
public class ChatApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimpMessageSendingOperations messagingTemplate;

    @Test
    public void testSendMessage() throws Exception {
//        // 테스트할 메시지
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setType(ChatMessage.MessageType.ENTER);
//        chatMessage.setSender("John");
//        chatMessage.setRoomId("123");
//
//        // WebSocket 메시지 전송 요청
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/chat/message")
//                .content(asJsonString(chatMessage))
//                .contentType("application/json"))
//            .andExpect(MockMvcResultMatchers.status().isOk());
//
//        // WebSocket 메시지가 정상적으로 전송되는지 확인
//        ArgumentCaptor<String> destinationCaptor = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<Object> messageCaptor = ArgumentCaptor.forClass(Object.class);
//        verify(messagingTemplate, times(1)).convertAndSend(destinationCaptor.capture(), messageCaptor.capture());
//
//        String destination = destinationCaptor.getValue();
//        ChatMessage sentMessage = (ChatMessage) messageCaptor.getValue();
//
//        // 예상 결과와 실제 전송된 메시지를 비교하여 테스트 결과 확인
//        // 이 부분은 실제 전송되는 메시지에 따라 수정해야합니다.
//        assertThat(destination).isEqualTo("/sub/chat/room/123");
//        assertThat(sentMessage.getType()).isEqualTo(ChatMessage.MessageType.ENTER);
//        assertThat(sentMessage.getSender()).isEqualTo("John");
    }

    // 객체를 JSON 문자열로 변환하는 유틸리티 메서드
    private static String asJsonString(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
