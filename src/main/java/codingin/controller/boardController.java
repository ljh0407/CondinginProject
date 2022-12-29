package codingin.controller;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.CategoryDto;
import codingin.domain.dto.PageDto;
import codingin.service.BoardService;
import codingin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class boardController {  //게시판
    //====================서비스=========================//
    @Autowired
    private BoardService boardService;
    //=============================================//

    @PostMapping("/setboard")   //1. 개별 글쓰기
    public boolean setboard(BoardDto boardDto) { return boardService.setboard(boardDto); }

    @PostMapping("/getboardlist")   //2. 글 출력하기
    public PageDto getboardlist(@RequestBody PageDto pageDto){
        return boardService.getboardlist(pageDto);
    }

    @GetMapping("/getbview")    //3.게시물 개별조회
    public BoardDto getbview (@RequestParam("bno") int bno){ return boardService.getboard(bno); }

    @DeleteMapping("/delboard") //4.게시물 삭제하기
    public boolean delboard(@RequestParam("bno") int bno){
        return  boardService.deleteboard(bno);
    }

    @PutMapping("/upboard")  //5. 게시물 수정하기
    public boolean upboard( BoardDto boardDto){
        return boardService.bupboard(boardDto);
    }

    @GetMapping("/getcategory") //6.  카테고리 출력하기
    public List<CategoryDto> categorylist(){
        return boardService.bcategoryList();
    }

    @GetMapping("/getdesclist") // 7. 최신글 4개 가져오기
    public List<BoardDto> getdesclist(@RequestParam("cno") int cno){
        return boardService.getdesclist(cno);
    }

    @GetMapping("/setview")//8. 조회수 증가하기
    public void setview(@RequestParam("bno") int bno){
        boardService.setview(bno);
    }

}