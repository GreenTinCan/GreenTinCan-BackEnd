package server.protalktime.categories.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.protalktime.common.model.BaseTimeEntity;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id")
    private Long id;

    @Column(name = "career_name", unique = true)
    private String name;

    private Career(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public static Career create(Long id, String name){
        return new Career(id, name);
    }
}
