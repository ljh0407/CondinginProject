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

    @Column(nullable = false)// 12.07 삭제된 필드 복구
    private String memail;

    @Column
    private String mnick;   //닉네임

    @Column
    private String mpassword; //비밀번호

    @Column
    private String mprofile;    //프로필

    @Column
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

    //주혁 좋아요 엔티티 연관관계 2022 12-06
    @ManyToOne
    @JoinColumn(name="uno")
    @ToString.Exclude
    private  UpdownEntity updownEntity;


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
                .mprofile( this.mprofile )
                .mnick( this.mnick )
                .mlevel( this.mlevel)
                .build();
    }
}
