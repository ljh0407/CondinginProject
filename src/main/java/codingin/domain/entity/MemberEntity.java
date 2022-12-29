package codingin.domain.entity;
import codingin.domain.BaseEntity;
import codingin.domain.dto.MemberDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 엔티티 정의
@Table(name = "member") // 테이블명 정의
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class MemberEntity extends BaseEntity {  //회원
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno; //회원번호

    @Column(nullable = false)
    private String memail;  //회원 메일

    @Column
    private String mnick;   //닉네임

    @Column
    private String mpassword; //비밀번호

    @Column
    private String mphone;  //전화번호

    @Column
    private String mprofile;    //프로필 파일명

    private String mlevel; //로그인한 소셜 확인

    @OneToMany(mappedBy = "memberEntity" ) // [ 1:n] PK 에 해당 어노테이션   mappedBy="fk필드명"
    @Builder.Default // 빌더 사용시 해당 필드의 초기값 설정
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    //양방향[일대일]
    @OneToMany(mappedBy = "lfrom")  //보내는 사람
    @Builder.Default // 빌더 사용시 해당 필드의 초기값 설정
    private List<LetterEntity> lfromlist = new ArrayList<>();

    @OneToMany(mappedBy = "lto")    //받는 사람
    @Builder.Default // 빌더 사용시 해당 필드의 초기값 설정
    private List<LetterEntity>ltolist = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity")   //댓글
    @Builder.Default
    private List<ReplyEntity>replyEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity")   //대댓글
    @Builder.Default
    private List<RereplyEntity>rereplyEntityList = new ArrayList<>();

    public MemberDto toDto(){
        return MemberDto
                .builder()
                .mno( this.mno )
                .mpassword( this.mpassword )
                .memail(this.memail)
                .mnick( this.mnick )
                .mlevel( this.mlevel)
                .build();
    }
}
