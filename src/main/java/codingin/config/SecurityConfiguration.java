package codingin.config;

import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  // 12.07 고은시 설정 컴포넌트 주입
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired  //12.07 고은시 멤버서비스 호출
    private MemberService memberService;

    @Override   // 12.07 고은시 인증(로그인) 관련 메소드 재정의
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //auth.userDetailsService( memberService ).passwordEncoder( new BCryptPasswordEncoder() );
        //멤버서비스에서 userDetailsService구현제를 loadUserByUsername에서 구현
    }

    //12.07 고은시 http를 통한 보안은 html완성 후 진행


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout()                                           // 로그아웃 보안 설정
                .logoutRequestMatcher( new AntPathRequestMatcher("/member/logout"))  // 12.08 고은시 로그아웃 처리 URL 정의
                .logoutSuccessUrl("/")                          // 로그아웃 성공했을때 이동할 URL
                .invalidateHttpSession( true )                  // 세션초기화  [ principal 초기화 ]
                .and()  // 기능 구분
                .csrf() // 요청 위조 방지
                .ignoringAntMatchers("/member/signup")
                .ignoringAntMatchers("/letter/setletter")
                .ignoringAntMatchers("/board/bwrite")
                .and()
                .oauth2Login() // 소셜 로그인 보안 설정
                .defaultSuccessUrl("/")// 소셜 로그인 성공시 이동하는 URL
                .userInfoEndpoint()// Endpoint (종착점) : 소셜 회원정보를 들어오는곳
                .userService( memberService );// 해당 서비스  loadUser 메소드 구현
    }
}
