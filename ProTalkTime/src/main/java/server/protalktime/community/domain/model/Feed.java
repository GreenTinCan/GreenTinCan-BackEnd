package server.protalktime.community.domain.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import server.protalktime.member.domain.model.Member;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedImages> feedImages = new ArrayList<>();

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedTag> feedTags = new ArrayList<>();

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedLike> feedLikes = new ArrayList<>();



    //==연관관계 메소드==//
    public void addFeedImage(String feedImageUrl) {
        FeedImages feedImage = new FeedImages(this, feedImageUrl);
        feedImage.setFeed(this);
        this.feedImages.add(feedImage);
    }

    public void addTag(Tag tag) {
        FeedTag feedTag = new FeedTag();
        feedTag.setTagAndFeed(tag, this);
    }


}
