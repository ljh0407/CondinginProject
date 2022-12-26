package codingin.controller;

import codingin.domain.dto.LetterDto;
import codingin.domain.dto.PageDto;
import codingin.domain.entity.LetterEntity;
import codingin.domain.entity.LetterRepository;
import codingin.domain.entity.MemberRepository;
import codingin.service.LetterService;
import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/letter")
public class LetterController {
    @Autowired
    private LetterService letterService;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LetterRepository letterRepository;

// ------------------------- 기능 ------------------------------------

    @PostMapping("/setletter")  // 1. 쪽지쓰기
    public boolean setletter(@RequestBody LetterDto letterDto){
     System.out.println("확인 : " + letterDto.toString() );
       return letterService.setletter(letterDto);
    }

    @GetMapping("/getlist") // 2. 쪽지 리스트 출력
    public List<LetterDto> letterlist(){
        return letterService.letterlist();
    }

} // end

