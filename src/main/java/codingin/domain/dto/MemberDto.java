package codingin.domain.dto;
import codingin.domain.entity.MemberEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberDto {
    private int mno;
    private String mpassword; //비밀번호
    private String mphone;  //전화번호
    private String mprofile;    //프로필
    private String mnick;   //닉네임
    private String mlevel; //회원등급

    public MemberEntity toEntity(){
        return MemberEntity
                .builder()
                .mno( this.mno )
                .mpassword( this.mpassword )
                .mphone( this.mphone )
                .mprofile( this.mprofile )
                .mnick( this.mnick)
                .mlevel( this.mlevel)
                .build();
    }
}
