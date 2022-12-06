package codingin.controller;

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
    @GetMapping("/index")    //인덱스 html 불러오기
    public Resource getindex(){
        return new ClassPathResource("templates/index.html");
    }



    //1 주혁 좋아요 싫어요 테스트



}
