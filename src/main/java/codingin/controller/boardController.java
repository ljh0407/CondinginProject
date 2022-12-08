package codingin.controller;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.UpdownDto;
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
   @GetMapping("/")    //인덱스 html 불러오기
   public Resource index(){
       return new ClassPathResource("templates/index.html");
    }

    //====================서비스=========================//

    //controller
    //1. 개별 글쓰기  12.5 최예은
    // 썸머노트를 사용 할 예정인데 혹시 지장이 있을까요?
    @PostMapping("/bwrite")
    public int bwrite(BoardDto boardDto){
            System.out.println("boardController 1.개별 글쓰기 boardDto 확인 : "+boardDto);
        int result = boardService.bwrite(boardDto);
            System.out.println("boardController 1. 개별 글 쓰기 result 확인 : " + result);
        return result;
    }

    //2. 글 출력하기 12.5 최예은
    @GetMapping("/blist")
    public List<BoardDto> blist(){
       return boardService.blist();
    }


    //3. 개별 글 보기 12.6 최예은
    @GetMapping("/getboard")
    public BoardDto getboard(@RequestParam("bno")int bno){
       return boardService.getboard(bno);
   }


    //4. 글 수정하기 12.6 최예은
    @PutMapping("/upboard")
    public boolean upboard(@RequestBody BoardDto boardDto){
       return boardService.upboard(boardDto);
    }

    //5. 글 삭제하기 12.6 최예은

    @GetMapping("/deleteboard")
    public boolean deleteboard(@RequestParam("bno")int bno){
       return boardService.deleteboard(bno);
    }

    // 주혁 12-06 테스트 경로
    @GetMapping("/updown")
    public  Resource updown() {return  new ClassPathResource("templates/updown.html");}

//    @PostMapping("/postupdown")
//    public  int updown(@RequestParam("bno") int bno , @RequestParam("liketype") int liketype ) {
//       return  boardService.postupdown(  bno ,  liketype);
//    }





}

