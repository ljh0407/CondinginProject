package codingin.controller;

import codingin.domain.dto.MemberDto;
import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;


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
    // 회원정보수정
    @PostMapping("/setmupdate")
    public boolean setmupdate(MemberDto memberDto){
        System.out.println("멤버확인"+memberDto.toString());
       return memberService.setmupdate(memberDto);
   }

}
