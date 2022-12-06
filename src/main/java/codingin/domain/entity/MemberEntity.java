package codingin.domain.entity;
import codingin.domain.BaseEntity;
import codingin.domain.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 엔티티 정의
@Table(name = "member") // 테이블명 정의
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno; //회원번호

    @Column(nullable = false)
    private String memail;
    @Column( nullable = false ) // not null
    private String mnick;   //닉네임

    @Column( nullable = false ) // not null
    private String mpassword; //비밀번호

    @Column( nullable = false ) // not null
    private String mphone;  //전화번호

    @Column( nullable = false ) // not null
    private String mprofile;    //프로필

    private String mlevel; //회원등급

    @OneToMany(mappedBy = "memberEntity" ) // [ 1:n] PK 에 해당 어노테이션   mappedBy="fk필드명"
    @Builder.Default // 빌더 사용시 해당 필드의 초기값 설정
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    //양방향[일대일]
    @OneToMany(mappedBy = "lfrom")
    @Builder.Default // 빌더 사용시 해당 필드의 초기값 설정
    private List<LetterEntity> lfromlist = new ArrayList<>();

    @OneToMany(mappedBy = "lto")
    @Builder.Default // 빌더 사용시 해당 필드의 초기값 설정
    private List<LetterEntity>ltolist = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity")
    @Builder.Default
    private List<ReplyEntity>replyEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity")
    @Builder.Default
    private List<RereplyEntity>rereplyEntityList = new ArrayList<>();

//    //양방향[일대일]
//    @OneToOne
//    @JoinColumn(name = "lfrom") // fk 필드명
//    @Builder.Default
//    private String lfrom; //보내는사람




    public MemberDto toDto(){
        return MemberDto
                .builder()
                .mno( this.mno )
                .mpassword( this.mpassword )
                .mphone( this.mphone )
                .mprofile( this.mprofile )
                .mnick( this.mnick )
                .mlevel( this.mlevel)
                .build();
    }
}
