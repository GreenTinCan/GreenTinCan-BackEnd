package server.protalktime.chat.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import server.protalktime.common.model.BaseTimeEntity;

@Getter
@Entity
public class Room extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name="room_name")
    private String name;

    @Column(name="room_name")
    private Boolean oneToOneChat;
}
