package codingin.service;

import codingin.domain.dto.LetterDto;
import codingin.domain.entity.LetterEntity;
import codingin.domain.entity.LetterRepository;
import codingin.domain.entity.MemberEntity;
import codingin.domain.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            letterEntity.setLto(memberEntity); // 받는사람 저장
            memberEntity.getLtolist().add(letterEntity); // 멤버 엔티티에서 받는사람정보 가져와서 쪽지엔티티에다 저장

            letterEntity.setLfrom(memberEntity); // 보낸사람 저장
            memberEntity.getLfromlist().add(letterEntity);  // 멤버 엔티티에서 보낸사람정보 가져와서 쪽지엔티티에다 저장

            System.out.println("쪽지 : "+letterEntity.toString());

            return true;
        }
        else {return false;}
    }

    @Transactional
    // 2. 쪽지리스트 출력
    public List<LetterDto>letterList(@RequestBody LetterDto letterDto ){
        List<LetterEntity> letterEntityList = null;
            letterEntityList = letterRepository.findAll();
            List<LetterDto> letterDtoList = new ArrayList<>();
            for(LetterEntity letterEntity : letterEntityList){
                letterDtoList.add(letterEntity.toDto());
            }
        System.out.println("test");
        return letterDtoList;
    }


}


