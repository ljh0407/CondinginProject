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




    // 1. 쪽지쓰기
    @Transactional
    public boolean setletter( LetterDto letterDto){
        MemberEntity memberEntity = memberService.getEntity(); // 멤버엔티티에서 회원정보 가져오기
        if( memberEntity == null ){return false;}


        LetterEntity letterEntity = letterRepository.save( letterDto.toEntity()); // dto --> entitiy 반환
        if (letterEntity.getLno() > 0 ){ // 보낸 쪽지번호가 0이 아니면 성공


            letterEntity.setLfrom(memberEntity); //             보내는사람
            memberEntity.getLfromlist().add(letterEntity); // 멤버 엔티티에서 받는사람정보 가져와서 쪽지엔티티에다 저장


            Optional<MemberEntity> optional = memberRepository.findByMemail( letterDto.getLto() );
            if(!optional.isPresent()){return false;} //로그인 확인 안되면 null 반환
            MemberEntity toMemberEntity =  optional.get();


            letterEntity.setLto( toMemberEntity );
            toMemberEntity.getLtolist().add( letterEntity );        // 받는사람

            System.out.println("쪽지 : "+letterEntity.toString());

            return true;
        }
        else {return false;}
    }

    @Transactional
    // 2. 쪽지리스트 출력
    public List<LetterDto> letterlist(){

        MemberEntity memberEntity =  memberService.getEntity();

        List<LetterEntity> elist =  memberEntity.getLtolist();
        List<LetterDto> dlist = new ArrayList<>();
        for(LetterEntity entity : elist){ //페이지클래스를 보드엔티티에 저장
            dlist.add( entity.toDto());  //보드디티오에 엔티티를 저장

        }

//        Page<LetterEntity> elist = null; //게시물 먼저 선언함
//        //사용자 기준으로 1을 입력해서 -1해주기 표시 게시물수 2 , 내림차순(bno기준)
//        Pageable pageable = PageRequest.of(pageDto.getPage()-1,5, Sort.by(Sort.Direction.DESC,"lno")) ; //페이징설정
//        //PageRequest.of(현재페이지번호, 표시할레코드수,정렬)
//        //검색여부      리포지토리에서 조작한 sql문 호출
//        elist = LetterRepository.findbylfrom
//        //elist = LetterRepository.findbylfrom( pageDto.getLno() ,pageDto.getKey(), pageDto.getKeyword(),pageable);
//        //view에 표시할 페이징번호 버튼 수
//        List<LetterDto> dlist = new ArrayList<LetterDto>();//컨트롤에게 전달할 때 형변한 하기 위한 그릇
//        for(LetterEntity entity : elist){ //페이지클래스를 보드엔티티에 저장
//            dlist.add(entity.toDto()); } //보드디티오에 엔티티를 저장
//        //리액트 전달
//        pageDto.setList(dlist); // 게시물 리스트
//        pageDto.setTotalletters(elist.getTotalElements());    //전체 게시물 수
        return dlist;
    }
}


