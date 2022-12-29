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
public class LetterController { //쪽지

    @Autowired
    private LetterService letterService;

// ----------------------------------------------------------------//

    @PostMapping("/setletter")  // 1. 쪽지쓰기
    public boolean setletter(@RequestBody LetterDto letterDto){
       return letterService.setletter(letterDto);
    }

    @GetMapping("/fromletter") // 보낸 쪽지리스트 출력
    public List<LetterDto> fromlist( @RequestParam("page") int page ){
        return letterService.fromlist(page);
    }

    @GetMapping("/toletter") // 받은 쪽지리스트 출력
    public List<LetterDto> tolist(@RequestParam("page") int page){
        return letterService.tolist(page);
    }

} // end



















