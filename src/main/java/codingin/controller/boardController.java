package codingin.controller;

import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class boardController {
    //=============================================//
    @Autowired
    private MemberService memberService;
//    //=============================================//
//    @GetMapping("/")    //인덱스 html 불러오기
//    public Resource index(){
//        return new ClassPathResource("templates/index.html");
//    }
}

