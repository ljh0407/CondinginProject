package codingin.controller;

import codingin.domain.dto.MemberDto;
import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    // 12.20 고은시 회원정보등록
    @GetMapping("/setmupdate")
    public boolean setmupdate(MemberDto memberDto){
        return memberService.setmupdate(memberDto);
    }
}
