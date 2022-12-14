package codingin.service;

import codingin.domain.dto.MemberDto;
import codingin.domain.dto.OauthDto;
import codingin.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MemberService implements  OAuth2UserService< OAuth2UserRequest , OAuth2User> {
    //=============전역변수=============================//
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LetterRepository letterRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private RereplyRepository rereplyRepository;
    @Autowired
    private  UpdownRepository updownRepository;    

    //====================================================//
    @Override   // 12.07 고은시 자동생성_로그인 성공한 소셜 회원 정보 받는 메소드
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("1.**"+userRequest.toString());
        // 1. 인증[로그인] 결과 정보 요청
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser( userRequest ); // oAuth2User.getAttributes()
        System.out.println("**2 = "+oAuth2User.toString());
        // 2. oauth2 클라이언트 식별 [ 카카오 vs 네이버 vs 구글 ]
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 3. 회원정보 담긴 객체명 [ JSON 형태 ]
        String oauth2UserInfo = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        // 4. Dto 처리
        OauthDto oauthDto = OauthDto.of( registrationId , oauth2UserInfo , oAuth2User.getAttributes() );

        // *. Db 처리 1. 이메일로 엔티티 검색 [ 가입  or 기존회원 **구분 ]
        Optional< MemberEntity > optional = memberRepository.findByMemail(oauthDto.getMemail());
        MemberEntity memberEntity = null;
        if( optional.isPresent() ) { // 기존회원이면 // Optional 클래스 [ null 예외처리 방지 ] \
            memberEntity = optional.get();
        }else{ // 기존회원이 아니면 [ 가입 ]
            memberEntity = memberRepository.save( oauthDto.toEntity() );
        }
        // 권한부여
        Set<GrantedAuthority> authorities   = new HashSet<>();
        authorities.add( new SimpleGrantedAuthority( memberEntity.getMlevel() ) );
        // 5. 반환 MemberDto[ 일반회원 vs oauth : 통합회원 - loginDto ]
        MemberDto memberDto = new MemberDto();
        memberDto.setMemail( memberEntity.getMemail() );
        memberDto.setAuthorities( authorities );
        memberDto.setAttributes( oauthDto.getAttributes() );

        return memberDto;
    }

    // 2. 로그인 여부 판단 메소드
    public  String getloginMno() {
        // 1. 인증된 토큰 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("서비스"+authentication);
        // 2. 인증된 토큰 내용 확인
        Object principal = authentication.getPrincipal();  // Principal : 접근주체 [ UserDeatils(MemberDto) ]
        System.out.println("서비스"+principal);
        // 3. 토큰 내용에 따른 제어
        if (principal.equals("anonymousUser")) {  // anonymousUser 이면 로그인전
            System.out.println("서비스"+principal);
            return null;
        } else { // anonymousUser 아니면 로그인후
            MemberDto memberDto = (MemberDto) principal;
            System.out.println("서비스"+memberDto);
            return memberDto.getMemail()+"_"+memberDto.getAuthorities();
        }
    }

}