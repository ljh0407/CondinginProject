package codingin.domain.dto;
import codingin.domain.entity.MemberEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberDto implements UserDetails , OAuth2User {    //12.07 고은시 구현메소드 호출
    private int mno;
    private String mpassword; //비밀번호
    private String memail;      // 12.07 삭제된 필드 복구
    private String mphone;  //전화번호
    private String mprofile;    //프로필
    private String mnick;   //닉네임
    private String mlevel; //회원등급
    private Set<GrantedAuthority> authorities; // 인증 권한 [토큰]
    private Map<String, Object> attributes; // oauth2 인증 결과

    public MemberEntity toEntity(){
        return MemberEntity
                .builder()
                .mno( this.mno )
                .mpassword( this.mpassword )
                .memail(this.memail)    // 12.07 삭제된 필드 복구
                .mphone( this.mphone )
                .mprofile( this.mprofile )
                .mnick( this.mnick)
                .mlevel( this.mlevel)
                .build();
    }


    /* 12.07 고은시 --------------UserDetails----------------- */
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
