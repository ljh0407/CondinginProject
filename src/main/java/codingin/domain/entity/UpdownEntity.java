package codingin.domain.entity;


import codingin.domain.BaseEntity;
import codingin.domain.dto.UpdownDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //엔티티
@Table(name="updown") // 테이블명
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class UpdownEntity extends BaseEntity {  //좋아요 싫어요

    // 좋아요싫어요 엔티티 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int uno; //pk

    @Column
    private  int likeno; // 좋아요

    @Column(nullable = false)
    private  int dno; //싫어요

    @Column(nullable = false)
    private  int upview; // 좋아요나 싫어요 눌렀을때 증가

    @OneToMany(mappedBy = "updownEntity")
    @Builder.Default
    private List<MemberEntity> memberEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "updownEntity")
    @Builder.Default
    private  List<BoardEntity> boardEntityList = new ArrayList<>();

    private UpdownDto toDto(){
        return  UpdownDto
                .builder()
                .uno(this.uno)
                .likeno(this.likeno)
                .dno(this.dno)
                .upview(this.upview)
                .build();
    }



}
