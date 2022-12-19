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
    @PostMapping("/setboard")
    public boolean setboard(BoardDto boardDto) { //js에서 json으로 받을 예정이라 @RequestBody로 받아야 함.
        System.out.println("boardController 1.개별 글쓰기 boardDto 확인 : " + boardDto);
        boolean result = boardService.setboard(boardDto);
        System.out.println("boardController 1. 개별 글 쓰기 result 확인 : " + result);
        return boardService.setboard(boardDto);
    }
    //2. 글 출력하기 12.5 최예은
    @PostMapping("/getboardlist")
    public PageDto getboardlist(@RequestBody PageDto pageDto){
        System.out.println("BoardController 2. 글 출력하기 pageDto확인하기 : " + pageDto);
        System.out.println("BoardController 2. 글 출력하기 pageDto확인하기 : " + pageDto.toString());
        return boardService.getboardlist(pageDto);
    }

    //3.게시물 개별조회
    @GetMapping("/getbview")
    public BoardDto getbview (@RequestParam("bno") int bno){
        System.out.println("컨트로 1 : **"+bno);
        return boardService.getboard(bno);    }


    //4.게시물 삭제하기
    @DeleteMapping("/delboard")
    public boolean delboard(@RequestParam("bno") int bno){

        return  boardService.deleteboard(bno);
    }

    //5. 게시물 수정하기
   @PutMapping("/upboard")
    public boolean upboard( BoardDto boardDto){
       System.out.println("컨트로 2 : **"+boardDto);
        return boardService.bupboard(boardDto);
   }
    //6.  카테고리 출력하기 12.15 최예은 추가
    @GetMapping("/getcategory")
    public List<CategoryDto> categorylist(){
        System.out.println("BoardController 6. 카테고리 출력하기 categorylist 확인");
        return boardService.bcategoryList();
    }

    //7.각 카테고리의 최신 글 가져오기 12.19 최예은 추가
   /* @GetMapping("/getlimitdesc")
    public List<BoardDto> getlimitdesc(@RequestParam int cno){
        return boardService.getlimitdesc(cno);
    }*/

}
