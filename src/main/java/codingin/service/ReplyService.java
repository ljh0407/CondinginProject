package codingin.service;

import codingin.domain.dto.ReplyDto;
import codingin.domain.entity.ReplyEntity;
import codingin.domain.entity.ReplyRepository;
import codingin.domain.entity.RereplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



    // 1. 댓글 등록하기
    public boolean setreply(int bno){
        return true;

    }


    //2. 댓글 출력하기





}
