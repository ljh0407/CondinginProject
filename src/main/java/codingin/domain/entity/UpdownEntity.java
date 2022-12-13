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

public class UpdownEntity extends BaseEntity {

    //12-06 주혁 좋아요싫어요 엔티티 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int uno; //pk

    @Column
    private  int liketype; // 좋아요


//    @ManyToOne
//    @Builder.Default
//    private MemberEntity memberEntity;
//
//    @ManyToOne
//    @Builder.Default
//    private  List<BoardEntity> boardEntityList = new ArrayList<>();

    private UpdownDto toDto(){
        return  UpdownDto
                .builder()
                .uno(this.uno)
                .liketype(this.liketype)
                .build();
    }



}
