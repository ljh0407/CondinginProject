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
        System.out.println(letterDto);
        LetterEntity letterEntity = letterRepository.save(letterDto.toEntity());
        if(letterEntity.getLno() != 0){
           return true;
        }else {return false;}
    }

    @Transactional
    // 2. 쪽지리스트 출력
    public List<LetterDto>letterList(int lno, String ltitle, String lcontent ){
        List<LetterEntity> letterEntityList = null;
        if(lno != 0){
            letterEntityList = letterRepository.findAll();
            List<LetterDto> letterDtoList = new ArrayList<>();
            for(LetterEntity letterEntity : letterEntityList){
                letterDtoList.add( letterEntity.toDto());
            }
            return letterDtoList;
        }
        return null;
    }


}


