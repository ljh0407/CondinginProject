package codingin.controller;

import codingin.domain.dto.LetterDto;
import codingin.domain.entity.MemberRepository;
import codingin.service.LetterService;
import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    //---------------------- 페이지로드 -----------------------
    // 이종훈 12/5 쪽지등록
    @GetMapping("/write")
        public Resource getwrite(){ return new ClassPathResource("templates/letter/letter.html");
        }
    @GetMapping("/list")
        public Resource getlist(){ return new ClassPathResource("templates/letter/letter.html") ;}



// ------------------------- 기능 ------------------------------------

    // 1. 쪽지쓰기
    @PostMapping("/setletter")
    public boolean setletter(@RequestBody LetterDto letterDto){
     System.out.println("확인 : " + letterDto.toString() );
       return letterService.setletter(letterDto);
    }

    // 2. 쪽지 리스트 출력
    @GetMapping("/getlist")
    public List<LetterDto>letterList(
            @RequestParam("lno") int lno,
            @RequestParam("ltitle") String ltitle,
            @RequestParam("lcontent") String lcontent
            ) {
        System.out.println("리스트 출력 : ");
        return letterService.letterList( lno , ltitle , lcontent );
    }

} // end

