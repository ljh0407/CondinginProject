package codingin.domain.entity;

import codingin.domain.BaseEntity;
import codingin.domain.dto.ReplyDto;
import codingin.domain.dto.RereplyDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity // 엔티티 정의
@Table(name = "reply") // 테이블명 정의
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class ReplyEntity extends BaseEntity {   //댓글
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;    //댓글 번호

    @Column
    private String rcomment; //댓글

    // 연관관계1 [ 회원번호[pk : mno] <--양방향--> 게시물번호[fk : rno]
    @ManyToOne
    @JoinColumn(name="mno") // 테이블에서 사용할 fk의 필드명 정의
    @ToString.Exclude
    private MemberEntity memberEntity;  // PK에 엔티티 객체

    // 연관관계2 [ pk bno ]  fk[ rno ]
    @ManyToOne
    @JoinColumn(name="bno")
    @ToString.Exclude
    private BoardEntity boardEntity;

    @OneToMany(mappedBy ="replyEntity")
    @Builder.Default
    private List<RereplyEntity>rereplyEntityList = new ArrayList<>();


    public ReplyDto toDto(){
        List<RereplyDto> list = new ArrayList<>();
        rereplyEntityList.forEach( ( e ) ->{  list.add( e.toDto() );  });

        return ReplyDto
                .builder()
                .rno( this.rno )
                .memail( memberEntity.getMemail() )
                .bfilename( memberEntity.getMprofile())
                .bdate(
                        this.getCdate().toLocalDate().toString().equals(LocalDateTime.now().toString())
                                ?
                                this.getCdate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                                :
                                this.getCdate().toLocalDate().toString()
                )
                .rcomment( this.rcomment)
                .rereplyDtos( list )
                .build();
    }
}
