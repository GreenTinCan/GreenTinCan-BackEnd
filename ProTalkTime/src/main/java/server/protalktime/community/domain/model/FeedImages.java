package server.protalktime.community.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import server.protalktime.common.model.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedImages extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_images_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Column(name = "feed_image_url")
    private String feedImageUrl = "url";

    public FeedImages(Feed feed, String feedImageUrl) {
        this.feed = feed;
        this.feedImageUrl = feedImageUrl;
    }

    public static FeedImages create(Feed feed, String feedImageUrl) { return new FeedImages(feed, feedImageUrl); }
}