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
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.protalktime.chat.domain.model.Room;
import server.protalktime.gather.dto.GatherDtos.GatherCreateRequestDto;
import server.protalktime.member.domain.model.Member;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Gather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gather_id")
    private Long id;

    private String title;
    private String script;
    private String location; // 관악 ,여의도 강남
    private String type; // 독서 , 스포츠
    private Boolean offline;
    private String allowedsex; //male , female , male and female

    @Column(name = "gather_doctor_number")
    private int dNum;
    @Column(name = "gather_accountant_number")
    private int aNum;
    @Column(name = "gather_lawyer_number")
    private int lNum;
    @Column(name = "gather_judical_number")
    private int jNum;

    @Column(name = "gather_male_number")
    private int maleNum;
    @Column(name = "gather_female_number")
    private int femaleNum;

    private int maxNumber;

    private int currentNumber;

    private String imageUrl;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "gather",cascade = CascadeType.PERSIST)
    private Room room;
    public Gather(GatherCreateRequestDto requestDto,Member member,Room room){
        this.member = member;
        this.room = room;
        this.title = requestDto.getTitle();
        this.script = requestDto.getScript();
        this.imageUrl = requestDto.getImageUrl();
        this.location = requestDto.getLocation();
        this.type = requestDto.getType();
        this.offline = requestDto.getOffline();
        this.allowedsex = requestDto.getAllowedsex();
        this.maxNumber = requestDto.getMaxNumber();
    }

    public void modifyGatherBoardByEnterMember(Member member) {
        this.currentNumber += 1;
        String memberCareer = member.getCareer();
        String memberSex = member.getSex();
        if (memberSex.equals("male")) {
            maleNum += 1;
        } else {
            femaleNum += 1;
        }
        switch (memberCareer) {
            case "doctor" -> this.dNum += 1;
            case "accountant" -> this.aNum += 1;
            case "lawyer" -> this.lNum += 1;
            case "judicial" -> this.jNum += 1;
        }

    }

    public String checkMemberEnter(Member member) {
        // logic for member enter check career sex whatever need
        String memberCareer = member.getCareer();
        if(this.currentNumber < this.maxNumber) return "ok";
        else return "no";
        //coul make code return room number when It can join gather
    }

}
