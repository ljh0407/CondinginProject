package codingin.config;

import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  // 12.07 고은시 설정 컴포넌트 주입
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired  //12.07 고은시 멤버서비스 호출
    private MemberService memberService;

    @Override   // 12.07 고은시 인증(로그인) 관련 메소드 재정의
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService( memberService ).passwordEncoder( new BCryptPasswordEncoder() );
        //멤버서비스에서 userDetailsService구현제를 loadUserByUsername에서 구현
    }

    //12.07 고은시 http를 통한 보안은 html완성 후 진행

}
