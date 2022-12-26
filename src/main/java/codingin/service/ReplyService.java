package codingin.service;

import codingin.domain.dto.ReplyDto;
import codingin.domain.entity.MemberEntity;
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
    @Transactional
    public boolean setreply(ReplyDto replyDto , int bno){
        BoardDto boardDto = boardService.getboard(bno); // 상세보기 함수에서 bno 가져오기
        // 이제 시작
        ReplyEntity replyEntity = replyRepository.save(replyDto.toEntity()); // dto entity로 형변환
        return true;
    }

}
