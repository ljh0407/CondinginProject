package codingin.controller;

import codingin.domain.dto.ReplyDto;
import codingin.domain.dto.RereplyDto;
import codingin.service.BoardService;
import codingin.service.MemberService;
import codingin.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //댓글
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;

    //1. 댓글 등록하기
    @PostMapping("/setreply")
    public boolean setreply( @RequestBody ReplyDto replyDto){
        //System.out.println("댓글 등록하기 controller");
        return replyService.setreply(replyDto );
    }

    //2. 댓글출력하기
    @GetMapping("/getrdplelist")
    public List<ReplyDto> getrdplelist(@RequestParam int bno ){
        //System.out.println("댓글 출력하기  controller");
        return replyService.getrdplelist(bno);
    }


    //3.댓글삭제하기
    @DeleteMapping("/deletereply")
    public boolean deletereply(@RequestParam("rno") int rno){
        System.out.println("삭제하기 controller");
        return replyService.deletereply(rno);//bno도???????
    }
    //4. 대댓글 작성하기

    @PostMapping("/setrerply")
    public boolean setrereply(@RequestBody RereplyDto rereplyDto){
        System.out.println("대댓글 controller");
        return replyService.setrereply(rereplyDto);
    }


}