package codingin.domain.entity;

import codingin.domain.BaseEntity;
import codingin.domain.dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Entity // 엔티티 정의
@Table(name = "board") // 테이블명 정의
@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder@ToString
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column( nullable = false ) // not null
    private String btitle; //제목
    @Column( nullable = false ) // not null
    private String  bcontent; //3. 내용
    @ColumnDefault("0")
    private int bview; // 8. 조회수 //12.12 최예은 String--<int 변경
    @ColumnDefault("0")
    private int bgood; //9. 추천수 //12.12 최예은 String--<int 변경
    @ColumnDefault("0")
    private int bbad; //10. 비추천 //12.12 최예은 String--<int 변경

     @Column
    private String bfile;       // 파일명

    // 연관관계1 [ 회원번호[pk : mno] <--양방향--> 게시물번호[fk : bno]
    @ManyToOne
    @JoinColumn(name="mno") // 테이블에서 사용할 fk의 필드명 정의
    @ToString.Exclude
    private MemberEntity memberEntity;  // PK에 엔티티 객체

    @ManyToOne // [ 1:n] FK에 해당 어노테이션
    @JoinColumn(name="cno")  // 카테고리 번호
    @ToString.Exclude
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy="boardEntity")
    @Builder.Default
    private List<ReplyEntity>  replyEntityList = new ArrayList<>();

    //주혁 좋아요 엔티티 연관관계 2022 12-06
    @ManyToOne
    @JoinColumn(name="uno")
    @ToString.Exclude
    private  UpdownEntity updownEntity;

    public BoardDto toDto(){
        return BoardDto
                .builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .bview(this.bview)
                .bgood(this.bgood)
                .bbad(this.bbad)
               .bdate(
                        this.getCdate().toLocalDate().toString().equals(LocalDateTime.now().toString())
                                ?
                                this.getCdate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                                :
                        this.getCdate().toLocalDate().toString()
                ) //12.12 최예은 작성시간 추가
                .mprofile(this.getMemberEntity().getMprofile()) // 12.12  최예은 프로필사진 추가
                .memail(this.getMemberEntity().getMemail())
                .mnick(this.memberEntity.getMnick())
                .build();
    }
}
