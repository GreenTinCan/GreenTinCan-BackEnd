package server.protalktime.gather.domain.model;

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
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.gather.dto.ShortMeetDtos.ShortMeetCreateRequestDto;
import server.protalktime.member.domain.model.Member;

@Getter @Setter
@Entity
@Table(name = "short_meet")
@NoArgsConstructor
public class ShortMeet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "short_meet_id")
    private Long id;

    @Column(name = "short_meet_type")
    private String type; // light , game only

    @Column(name = "short_meet_title")
    private String title;

    @Column(name = "short_meet_script")
    private String script;

    @Column(name = "short_meet_locatiom")
    private String location;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_id")
    private Room room ;
    public ShortMeet(ShortMeetCreateRequestDto requestDto,Member member,Room room){
        this.member = member;
        this.room = room;
        this.title = requestDto.getTitle();
        this.script = requestDto.getScript();
        this.location = requestDto.getLocation();
        this.type = requestDto.getType();
    }
}
