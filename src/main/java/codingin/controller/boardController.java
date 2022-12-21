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

    @PostMapping("/setboard")   //1. 개별 글쓰기
    public boolean setboard(BoardDto boardDto) { //js에서 json으로 받을 예정이라 @RequestBody로 받아야 함.
        return boardService.setboard(boardDto);
    }

    @PostMapping("/getboardlist")   //2. 글 출력하기
    public PageDto getboardlist(@RequestBody PageDto pageDto){
        return boardService.getboardlist(pageDto);
    }

    @GetMapping("/getbview")    //3.게시물 개별조회
    public BoardDto getbview (@RequestParam("bno") int bno){
        return boardService.getboard(bno);    }

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

    //7.각 카테고리의 최신 글 가져오기 12.19 최예은 추가
   /* @GetMapping("/getlimitdesc")
    public List<CategoryDto> getlimitdesc(@RequestParam int cno){
        return boardService.getlimitdesc(cno);
    }
*/
}
