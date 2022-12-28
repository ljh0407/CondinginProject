package codingin.controller;

import codingin.domain.dto.ReplyDto;
import codingin.domain.dto.RereplyDto;
import codingin.service.BoardService;
import codingin.service.MemberService;
import codingin.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {  //댓글

    @Autowired
    private ReplyService replyService;

    @PostMapping("/setreply")   //1. 댓글 등록하기
    public boolean setreply( @RequestBody ReplyDto replyDto){
        return replyService.setreply(replyDto );
    }

    @GetMapping("/getrdplelist")    //2. 댓글출력하기
    public List<ReplyDto> getrdplelist(@RequestParam int bno ){
        //System.out.println("댓글 출력하기  controller");
        return replyService.getrdplelist(bno);
    }

    @DeleteMapping("/deletereply")  //3.댓글삭제하기
    public boolean deletereply(@RequestParam("rno") int rno){
        System.out.println("삭제하기 controller");
        return replyService.deletereply(rno);//bno도???????
    }

    @PostMapping("/setrerply")  //4. 대댓글 작성하기
    public boolean setrereply(@RequestBody RereplyDto rereplyDto){
        System.out.println("대댓글 controller");
        return replyService.setrereply(rereplyDto);
    }

    //5. 대댓글 출력하기
//    @GetMapping("gerrerply")
//    public List<RereplyDto> getrereplylist(@RequestParam int rno){
//        System.out.println("대댓글 출력하기 controller");
//        return replyService.getrereplylist(rno);
//    }


    //6.대댓글 삭제하기



}