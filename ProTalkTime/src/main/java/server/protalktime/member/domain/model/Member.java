package server.protalktime.member.domain.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import server.protalktime.categories.domain.model.Career;
import server.protalktime.common.model.BaseTimeEntity;
import server.protalktime.gather.domain.model.Gather;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "nick_name", unique = true)
    private String nickName;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "work_place")
    private String workPlace;

    @Column(name = "get_question")
    @ColumnDefault("false")
    private boolean getQeustion;

    @Column(name = "one_to_one")
    @ColumnDefault("false")
    private boolean oneToOne;

    @Column(name = "married")
    private boolean married;

    @Column(name = "varified")
    @ColumnDefault("false")
    private boolean varified;

    @Column(name = "profile_url")
    @ColumnDefault("'url'")
    private String profileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "career_id")
    private Career career;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Gather> gathers = new ArrayList<>();

    @Builder
    private Member(Long id,
                   String nickName,
                   String realName,
                   String sex,
                   String workPlace,
                   boolean getQeustion,
                   boolean oneToOne,
                   boolean married,
                   boolean varified,
                   Career career){
        this.id = id;
        this.nickName = nickName;
        this.realName = realName;
        this.sex = sex;
        this.workPlace = workPlace;
        this.getQeustion = getQeustion;
        this.oneToOne = oneToOne;
        this.married = married;
        this.varified = varified;
        this.career = career;
    }

    public static Member create(Long id,
                                String nickName,
                                String realName,
                                String sex,
                                String workPlace,
                                boolean married,
                                Career career) {
        return Member.builder()
                .id(id)
                .nickName(nickName)
                .realName(realName)
                .sex(sex)
                .workPlace(workPlace)
                .married(married)
                .career(career)
                .build();
    }
}
