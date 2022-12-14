package codingin.domain.dto;
import codingin.domain.entity.MemberEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberDto implements UserDetails , OAuth2User {    //회원 구현메소드 호출
    private int mno;    //회원번호
    private String mpassword; //비밀번호
    private String memail;      //이메일
    //private String mphone;  // 전화번호 필드 삭제(소셜로그인이라 필요없음)
    private MultipartFile mprofile;    //프로필[업로드용]
    private String mfilename;   //첨부파일 [ 출력용 ]
    private String mnick;   //닉네임
    private String mlevel; //소셜
    private Set<GrantedAuthority> authorities; // 인증 권한 [토큰]
    private Map<String, Object> attributes; // oauth2 인증 결과

    public MemberEntity toEntity(){
        return MemberEntity
                .builder()
                .mno( this.mno )
                .mpassword( this.mpassword )
                .memail(this.memail)
                .mnick( this.mnick)
                .mlevel( this.mlevel)
                .build();
    }


    /* --------------UserDetails----------------- */
    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.mpassword;
    }

    @Override
    public String getUsername() {
        return this.memail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /* --------------OAuth2User----------------- */
    @Override
    public String getName() {  return this.memail; }
    @Override
    public Map<String, Object> getAttributes() {   return this.attributes; }
}
