package codingin.controller;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.CategoryDto;
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
    //====================서비스=========================//

    //controller
    //1. 개별 글쓰기  12.5 최예은
    // 썸머노트를 사용 할 예정인데 혹시 지장이 있을까요?
    @PostMapping("/setboard")
    public boolean setboard(BoardDto boardDto) { //js에서 json으로 받을 예정이라 @RequestBody로 받아야 함.
        return boardService.setboard(boardDto);
    }
    //2. 글 출력하기 12.5 최예은
    @PostMapping("/getboardlist")
    public PageDto getboardlist(@RequestBody PageDto pageDto){
        return boardService.getboardlist(pageDto);
    }


    //3.게시물 개별조회
    @GetMapping("/getbview")
    public BoardDto getbview (@RequestParam("bno") int bno){
        System.out.println("컨트로 1 : **"+bno);
        return boardService.getboard(bno);
    }


    //4.게시물 삭제하기
    @DeleteMapping("/delboard")
    public boolean delboard(@RequestParam("bno") int bno){

        return  boardService.deleteboard(bno);
    }

    //5. 게시물 수정하기
   @PutMapping("/upboard")
    public boolean upboard(BoardDto boardDto){
       System.out.println("컨트로 2 : **"+boardDto);
        return boardService.bupboard(boardDto);
   }

    //6.  카테고리 출력하기 12.15 최예은 추가
    @PostMapping("/getcategory")
    public List<CategoryDto> categorylist(){
        return boardService.bcategoryList();
    }
}

