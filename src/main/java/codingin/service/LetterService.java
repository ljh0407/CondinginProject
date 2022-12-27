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

            System.out.println("쪽지 : "+letterEntity.toString());

            return true;
        }
        else {return false;}
    }

    @Transactional  // 보낸 쪽지리스트 출력
    public List<LetterDto> fromlist(){
        //쪽지엔티티에 로그인 된 회원의 보낸 쪽지 가져오기
        List<LetterEntity> entityList = memberService.getEntity().getLfromlist();
        List<LetterDto> dtoList = new ArrayList<>();    //받은 쪽지 디티오리스트 생성
        for(LetterEntity letterEntity : entityList){    //쪽지 엔티티에 가져온 회원의 받은 쪽지함 담기
            System.out.println("쪽지서비스:"+dtoList);
            dtoList.add(letterEntity.toDto());  //디티오에 회원의 받은 쪽지 담기
        }
        return dtoList;
    }

    @Transactional  // 받은 쪽지리스트 출력
    public List<LetterDto> tolist(){
        //쪽지엔티티에 로그인 된 회원의 받은 쪽지 가져오기
        List<LetterEntity> entityList = memberService.getEntity().getLtolist();
        List<LetterDto> dtoList = new ArrayList<>();    //받은 쪽지 디티오리스트 생성
        for(LetterEntity letterEntity : entityList){    //쪽지 엔티티에 가져온 회원의 보낸 쪽지함 담기
            dtoList.add(letterEntity.toDto());
            System.out.println("쪽지서비스:"+dtoList);   //디티오에 회원의 보낸 쪽지 담기
        }
        return dtoList;
    }

    @Transactional // 쪽지 상세보기
    public LetterDto viewletter(int lno){  // 선택한 lno
        // 입력받은 쪽지 번호 엔티티검색
        Optional<LetterEntity> optional = letterRepository.findById(lno);
        if(optional.isPresent()){
            
            LetterEntity letterEntity = optional.get(); // letter엔티티에서 가져오기
            LetterDto letterDto = letterEntity.toDto(); // 디티오 -> 엔티티변환
            System.out.println("쪽지상세보기 : "+letterDto);
            return letterDto;
        }else {return null;}
    }

}


