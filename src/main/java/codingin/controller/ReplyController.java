package codingin.controller;

import codingin.domain.dto.ReplyDto;
import codingin.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //댓글
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;



    //1.댓글 등록하기
    @PostMapping("setreply")
    public boolean setReply(@RequestParam("bno") int bno){
        System.out.println("여기까지왔니?"); //안오네
        return replyService.setreply(bno);
    }

    //2. 댓글 출력하기





}
