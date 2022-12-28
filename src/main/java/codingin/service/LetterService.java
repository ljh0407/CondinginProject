package codingin.service;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.LetterDto;
import codingin.domain.dto.PageDto;
import codingin.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LetterService {
    @Autowired
    private LetterRepository letterRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Transactional  // 1. 쪽지쓰기
    public boolean setletter( LetterDto letterDto){
        MemberEntity memberEntity = memberService.getEntity(); // 멤버엔티티에서 회원정보 가져오기
        if( memberEntity == null ){return false;}


        LetterEntity letterEntity = letterRepository.save( letterDto.toEntity()); // dto --> entitiy 반환
        if (letterEntity.getLno() > 0 ){ // 보낸 쪽지번호가 0이 아니면 성공


            letterEntity.setLfrom(memberEntity); //보내는사람
            memberEntity.getLfromlist().add(letterEntity); // 멤버 엔티티에서 받는사람정보 가져와서 쪽지엔티티에다 저장


            Optional<MemberEntity> optional = memberRepository.findByMemail( letterDto.getLto() );
            if(!optional.isPresent()){return false;} //로그인 확인 안되면 null 반환
            MemberEntity toMemberEntity =  optional.get();


            letterEntity.setLto( toMemberEntity );
            toMemberEntity.getLtolist().add( letterEntity );        // 받는사람

            return true;
        }
        else {return false;}
    }

    @Transactional  // 보낸 쪽지리스트 출력
    public List<LetterDto> fromlist( int page ){
        Pageable pageable = PageRequest.of( page-1 , 5 ); // -1 : 현재페이지  5 : 한 페이지당 쪽지갯수

        Page<LetterEntity> entityList // Repository 에서 정보를 가져와야함 서비스에서 가져오면 페이징처리를 수동으로 만들어야함!!
                = letterRepository.findByFromLetter( memberService.getEntity().getMno() , pageable );
                // 멤버서비스에서 회원정보중 회원번호를 가져오고 // pageable도 가져온다.
        //사용자 기준으로 1을 입력해서 -1해주기 표시 게시물수 2 , 내림차순(lno)
       // PageRequest.of(현재페이지번호, 표시할레코드수,정렬)
         //view에 표시할 페이징번호 버튼 수
        List<LetterDto> ldto = new ArrayList<LetterDto>();//컨트롤에게 전달할 때 형변한 하기 위한 그릇
        for(LetterEntity lentity : entityList){ //페이지클래스를 letter엔티티에 저장
            ldto.add( lentity.toDto());  // ldto에 lentity를 변환해서 넣기
            ldto.get(0).setTotalletter(  entityList.getTotalElements() ); // setTotalletter : 전체 보낸 쪽지수
        } //보드디티오에 엔티티를 저장
        //리액트 전달
        return ldto;
    }

    @Transactional  // 받은 쪽지리스트 출력
    public List<LetterDto> tolist(int page){
        Pageable pageable = PageRequest.of( page-1 , 5 ); // -1 : 현재페이지  5 : 한 페이지당 쪽지갯수

        Page<LetterEntity> entityList // Repository 에서 정보를 가져와야함 서비스에서 가져오면 페이징처리를 수동으로 만들어야함!!
                = letterRepository.findByToLetter( memberService.getEntity().getMno() , pageable );
        // 멤버서비스에서 회원정보중 회원번호를 가져오고 // pageable도 가져온다.
        //사용자 기준으로 1을 입력해서 -1해주기 표시 게시물수 2 , 내림차순(lno)
        // PageRequest.of(현재페이지번호, 표시할레코드수,정렬)
        //view에 표시할 페이징번호 버튼 수
        //쪽지엔티티에 로그인 된 회원의 받은 쪽지 가져오기
        List<LetterDto> dtoList = new ArrayList<>();    //받은 쪽지 디티오리스트 생성
        for(LetterEntity letterEntity : entityList){    //쪽지 엔티티에 가져온 회원의 보낸 쪽지함 담기
            dtoList.add(letterEntity.toDto());
            dtoList.get(0).setTotalletter(  entityList.getTotalElements() ); // setTotalletter : 전체 보낸 쪽지수
        }

        if(dtoList == null){
            return null;
        }
        return dtoList;
    }
}


