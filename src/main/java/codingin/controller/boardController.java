package codingin.controller;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.PageDto;
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

    //    //====================페이지 [html]요청 로드=========================//
//    @GetMapping("/")    //인덱스 html 불러오기
//    public Resource index() {
//        return new ClassPathResource("templates/index.html");
//    }
//
//    //글쓰기 페이지
//    @GetMapping("/bwrite")
//    public Resource bwrite() {
//        return new ClassPathResource("templates/board/bwrite.html");
//    }
//
//    // 12.9 최예은 추가
//    //개별 글 조회하기 페이지 입니다.
//    @GetMapping("/toboard")
//    public Resource getboard() {
//        return new ClassPathResource("templates/board/bview.html");
//    }


    //====================서비스=========================//

    //controller
    //1. 개별 글쓰기  12.5 최예은
    // 썸머노트를 사용 할 예정인데 혹시 지장이 있을까요?
    @PostMapping("/setboard")
    public boolean setboard(BoardDto boardDto) { //js에서 json으로 받을 예정이라 @RequestBody로 받아야 함.
        System.out.println("boardController 1.개별 글쓰기 boardDto 확인 : " + boardDto);
        boolean result = boardService.setboard(boardDto);
        System.out.println("boardController 1. 개별 글 쓰기 result 확인 : " + result);
        return boardService.setboard(boardDto);
    }
    //2. 게시물 목록 조회 [페이지 , 검색] 최예은

    @PostMapping("/getboardlist")
    public PageDto getboardlist(@RequestBody PageDto pageDto){
        System.out.println("BoardController 2. 글 출력하기 pageDto확인하기 : " + pageDto);
        System.out.println("BoardController 2. 글 출력하기 pageDto확인하기 : " + pageDto.toString());
        return boardService.getboardlist(pageDto);
    }





    //6. 카테고리 출력하기




}

