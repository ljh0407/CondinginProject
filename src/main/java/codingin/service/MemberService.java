package codingin.service;

import codingin.domain.dto.MemberDto;
import codingin.domain.dto.OauthDto;
import codingin.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service
public class MemberService implements  OAuth2UserService< OAuth2UserRequest , OAuth2User> { //회원
    //==========================================//
    @Autowired
    private MemberRepository memberRepository;
    //첨부파일 경로
    String path = "C:\\Users\\504\\Desktop\\codingin\\build\\resources\\main\\static\\static\\media\\";  // C드라이브-> upload 폴더 생성

    //* 첨부파일 업로드 [ 1. 쓰기메소드 2. 수정메소드 ] 사용
    @Transactional              //  memberDto : 쓰기,수정 대상     MemberEntity:원본
    public boolean profileupload( MemberDto memberDto , MemberEntity memberEntity ){
        if( !memberDto.getMprofile().getOriginalFilename().equals("") ) { // ** 첨부파일 있을때
            // * 업로드 된 파일의 이름 [ 문제점 : 파일명 중복 ]
            String uuid = UUID.randomUUID().toString(); // 1. 난수생성
            String filename = uuid + "" + memberDto.getMprofile().getOriginalFilename(); // 2. 난수+파일명
            // * 첨부파일명 db 에 등록
            memberEntity.setMprofile(filename); // 해당 파일명 엔티티에 저장 // 3. 난수+파일명 엔티티 에 저장
            // * 첨부파일 업로드 // 3. 저장할 경로 [ 전역변수 ]
            try {
                File uploadfile = new File(path + filename);  // 4. 경로+파일명 [ 객체화 ]
                memberDto.getMprofile().transferTo(uploadfile);   // 5. 해당 객체 경로 로 업로드
            } catch (Exception e) {
                System.out.println("첨부파일 업로드 실패 "+e);
            }
            return  true;
        }else{ return  false;}
    }
    //====================================================//
    @Override   // 자동생성_로그인                   성공한 소셜 회원 정보 받는 메소드
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. 인증[로그인] 결과 정보 요청
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser( userRequest ); // oAuth2User.getAttributes()
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
        MemberDto memberDto = new MemberDto(); // 로그인 성공했을대 들어가는 정보들
        memberDto.setAuthorities( authorities );    //로그인
        memberDto.setAttributes( oauthDto.getAttributes() );    //세션
        memberDto.setMfilename(memberEntity.getMprofile()); //프로필
        memberDto.setMemail( memberEntity.getMemail() );    //이메일
        memberDto.setMno( memberEntity.getMno() );    //이메일
        if( memberEntity.getMnick() == null ){  //닉네임이 없으면
            memberDto.setMnick( memberEntity.getMemail() ); //이메일 출력
        }else{  //닉네임이 있으면
            memberDto.setMnick( memberEntity.getMnick() );  //닉네임 출력
        }
        return memberDto;
    }

    @Transactional  // 2. 로그인 여부 판단 메소드
    public  MemberDto getloginMno() {
        // 1. 인증된 토큰 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 2. 인증된 토큰 내용 확인
        Object principal = authentication.getPrincipal();  // Principal : 접근주체 [ UserDeatils(MemberDto) ]
        // 3. 토큰 내용에 따른 제어
        if (principal.equals("anonymousUser")) {  // anonymousUser 이면 로그인전
            return null;
        } else { // anonymousUser 아니면 로그인후
            MemberDto memberDto = (MemberDto) principal;    //멤버디티오에서 가져옴
            return memberDto;
        }
    }
    @Transactional  //엔티티에서 이메일 가져오고 로그인 토큰 반환(회원번호 호출)
    public MemberEntity getEntity(){
        //로그인정보 확인
        Object object = new SecurityContextHolder().getContext().getAuthentication().getPrincipal();
        if(object == null){return null;}    //회원정보가 없으면
        MemberDto memberDto = (MemberDto) object;   //오브젝트를 디티오 형변환
        //멤버디티오에 있는 이메일을 멤버엔티티에 저장
        Optional<MemberEntity> optional = memberRepository.findById(memberDto.getMno());
        if(!optional.isPresent()){return null;} //로그인 확인 안되면 null 반환
        return optional.get();  //로그인정보 확인되면 전부 반환
    }

    @Transactional  //회원수정 시 프로파일 업로드
    public boolean setmupdate(MemberDto memberDto ){
        String Memail = getloginMno().getMemail();    //Memail에 로그인 세션가져오기
        MemberEntity memberEntity = memberRepository.findByMemail(Memail).get();    //리포지토리에 설정한 이메일 찾기가져오기
        ///dto -> entity저장
        if(memberEntity.getMno() > 0 ) { //회원번호가 0보다 크면
            memberEntity.setMnick(memberDto.getMnick()); //디티오에서 가져온 닉네임 엔티티에 저장하기
            if (memberDto.getMprofile() != null) {   //디티오에 프로파일이 없으면
                profileupload(memberDto, memberEntity);  //파일업로드 메소드호출(디티오 엔티티)가져오기
                return true; //전부 리턴
            }
        }return false;
    }
    @Transactional  // 회원정보 출력하기
    public MemberDto profilelist() {
        int mno = getEntity().getMno(); //로그인된 토큰에서(함수) pk호출
        Optional<MemberEntity> optional = memberRepository.findById(getEntity().getMno());  //멤버엔티티에서 mno(pk)가져오기
        if (optional.isPresent()) {
            MemberEntity memberEntity = optional.get();
            MemberDto memberDto = memberEntity.toDto();
            return memberDto;
        } else {
            return null;
        }
    }
}