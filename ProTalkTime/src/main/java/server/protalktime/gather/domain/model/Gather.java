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

import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.member.domain.model.Member;

@Getter @Setter
@Table(name = "gather")
@Entity
public class Gather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gather_id")
    private Long id;

    private String title;
    private String script;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "gather",cascade = CascadeType.PERSIST)
    private Room room;
}
