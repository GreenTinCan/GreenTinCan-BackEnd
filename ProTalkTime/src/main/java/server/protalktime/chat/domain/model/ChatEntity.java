package server.protalktime.chat.domain.model;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import server.protalktime.chat.dto.ChatDtos.ChatRequestDto;
import server.protalktime.common.model.BaseTimeEntity;
import server.protalktime.member.domain.model.Member;

@Getter @Setter
@Entity
@Table(name = "chat")
public class ChatEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;
    public enum MessageType {
        ENTER, TALK,LEAVE
    }
    private MessageType type; // 메시지 타입

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    private String message; // 메시지
    public ChatEntity(Member memberById, Room roomById, ChatRequestDto requestDto) {
        this.member = memberById ;
        this.room = roomById;
        this.type = requestDto.getType();
        this.message = requestDto.getMessage();
    }

}
