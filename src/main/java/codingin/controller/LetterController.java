package codingin.controller;

import codingin.domain.dto.LetterDto;
import codingin.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/letter")
public class LetterController {
    @Autowired
    private LetterService letterService;

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
    public List<LetterDto>letterList(@RequestBody LetterDto letterDto){
        System.out.println("리스트 출력 : "+ letterDto.toString());
        return null;
    }

} // end

