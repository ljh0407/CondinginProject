package codingin.controller;

import codingin.domain.dto.BoardDto;
import codingin.service.BoardService;
import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class boardController {
    //=============================================//
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;
    //====================서비스=========================//

    //1. 개별 글쓰기  12.5 최예은
    // 썸머노트를 사용 할 예정인데 혹시 지장이 있을까요?
    @PostMapping("/setboard")
    public int bwrite(@RequestBody BoardDto boardDto) { //js에서 json으로 받을 예정이라 @RequestBody로 받아야 함.
        System.out.println("boardController 1.개별 글쓰기 boardDto 확인 : " + boardDto);
        int result = boardService.bwrite(boardDto);
        System.out.println("boardController 1. 개별 글 쓰기 result 확인 : " + result);
        return result;
    }
    //2. 글 출력하기 12.5 최예은
    @GetMapping("/getboardlist")
    public List<BoardDto> blist() {
        return boardService.blist();
    }
}

