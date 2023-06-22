package server.protalktime.chat.domain.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.protalktime.common.model.BaseTimeEntity;
import server.protalktime.gather.domain.model.Gather;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Room extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name="room_name")
    private String name;

    @Column(name="room_one_to_one_chat")
    private Boolean oneToOneChat;

    @Column(name="room_origin")
    private String origin; //  LIGHTING , OTO (1:1 ), GATHER

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private Gather gather;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomMember> roomMembers = new ArrayList<>();

    public Room(String name , Boolean oneToOneChat ,String givenOrigin) {
        this.name=name ;
        this.oneToOneChat = oneToOneChat ;
        this.origin = givenOrigin;
    }
    public void modifyRoomChatOption(Boolean option) {
        this.oneToOneChat = option;
    }
}
