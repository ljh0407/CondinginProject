package codingin.controller;

import codingin.domain.dto.ReplyDto;
import codingin.service.BoardService;
import codingin.service.MemberService;
import codingin.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //댓글
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;
    @PostMapping("/setreply")
    public boolean setreply(ReplyDto replyDto , int bno){
        System.out.println("넘어왔니?");
        return replyService.setreply(replyDto , bno);
    }

}
