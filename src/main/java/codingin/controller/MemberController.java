package codingin.controller;

import codingin.domain.dto.MemberDto;
import codingin.domain.entity.MemberEntity;
import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/member")
public class MemberController {
    //=============================================//
    @Autowired
    private MemberService memberService;

    //=============================================//
    @GetMapping("/getloginMno") // 1. 로그인 정보 확인
    public String getloginMno() {
        String result = memberService.getloginMno();
        return result;
    }

    @PostMapping("/setmupdate") // 회원정보수정
    public boolean setmupdate(MemberDto memberDto){
       return memberService.setmupdate(memberDto);
   }
   @GetMapping("/profile")   //12.21 고은시 회원정보 출력하기
   public List<MemberDto> profilelist(){
       return memberService.profilelist();
   }

}
