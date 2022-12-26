package codingin.service;

import codingin.domain.entity.ReplyRepository;
import codingin.domain.entity.RereplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    //댓글 고은시 12.22
public class ReplyService {
    //====================================================//
    @Autowired  //댓글
    private ReplyRepository replyRepository;
    @Autowired  //대댓글
    private RereplyRepository rereplyRepository;
    @Autowired  //멤버서비스
    private MemberService memberService;
    @Autowired  //보드서비스
    private BoardService boardService;
    //====================================================//

}
