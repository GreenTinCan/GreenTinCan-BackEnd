package server.protalktime.community.domain.model;

import lombok.Getter;
import lombok.Setter;
import server.protalktime.common.model.BaseTimeEntity;
import server.protalktime.member.domain.model.Member;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", length = 100)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "comment_content", length = 500)
    private String content;
}
