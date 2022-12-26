package codingin.config;

import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration  // 12.07 고은시 설정 컴포넌트 주입
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired  //12.07 고은시 멤버서비스 호출
    private MemberService memberService;

    @Override   // 12.07 고은시 인증(로그인) 관련 메소드 재정의
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //auth.userDetailsService( memberService ).passwordEncoder( new BCryptPasswordEncoder() );
        //멤버서비스에서 userDetailsService구현제를 loadUserByUsername에서 구현
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //(시큐리티 = axios = 컨트롤러)
        http
                .formLogin()                                        // 로그인 페이지 보안 설정
                .loginPage("/member/login")                     // 아이디와 비밀번호를 입력받을 URL [ 로그인 페이지  ]
                .loginProcessingUrl("/member/loadUser")        // 로그인을 처리할 URL [ 서비스 --> loadUserByUsername  ]
                .defaultSuccessUrl("/")                         // 로그인 성공했을때 이동할 URL
                .failureUrl("/member/login") // 로그인 실패시 이동할 URL
                .and()// 기능 구분
                .logout()                                           // 로그아웃 보안 설정
                .logoutRequestMatcher( new AntPathRequestMatcher("/member/logout"))  // 12.08 고은시 로그아웃 처리 URL 정의
                .logoutSuccessUrl("/")                          // 로그아웃 성공했을때 이동할 URL
                .invalidateHttpSession( true )                  // 세션초기화  [ principal 초기화 ]
                .and()  // 기능 구분
                .csrf() // 요청 위조 방지
                    .ignoringAntMatchers("/member/signup")  //로그인
                    .ignoringAntMatchers("/letter/setletter") // 쪽지 작성 post 사용
                    .ignoringAntMatchers("/board/setboard") // 게시글 작성 post 사용
                    .ignoringAntMatchers("/board/getboardlist")  // 글리스트 출력 post
                    .ignoringAntMatchers("/board/getcategory")  // 카테고리 리스트 post 사용
                    .ignoringAntMatchers("/board/getbview")  // 상세보기
                    .ignoringAntMatchers("/board/delboard")  // 삭제 url
                    .ignoringAntMatchers("/board/upboard") // 게시물수정 put 사용
                    .ignoringAntMatchers("/member/setmupdate") // 회원수정 put 사용
                    .ignoringAntMatchers("/member/upprofile") // 회원출력 get 사용
                    .ignoringAntMatchers("/reply/setreply") // 12.23 최예은 댓글작성 post 사용
                .and()
                .oauth2Login() // 소셜 로그인 보안 설정
                .defaultSuccessUrl("/")// 소셜 로그인 성공시 이동하는 URL
                .userInfoEndpoint()// Endpoint (종착점) : 소셜 회원정보를 들어오는곳
                .userService( memberService );// 해당 서비스  loadUser 메소드 구현
    }
}
