package codingin.service;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.ReplyDto;
import codingin.domain.entity.*;
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
    public boolean setreply(ReplyDto replyDto ){
        BoardDto boardDto = boardService.getboard( replyDto.getBno() ); // 상세보기 함수에서 bno 가져오기
        System.out.println( "ReplyService boardDto 확인 : " + boardDto.toString());
        System.out.println( "ReplyService replyDto 확인 : " + replyDto.toString() );

        // dto entity로 형변환
        //로그인을 한 경우에만 댓글 작성이 가능함 그러면 로그인을 했는지도 확인을 해야함.Mno가져와야함
        MemberEntity memberEntity = memberService.getEntity();
        if(memberEntity == null){return  false;} // 만약에 로그인을 하지 않으면 리턴
        System.out.println("ReplyService memberEntity 확인 : "+memberEntity);
        ReplyEntity replyEntity = replyRepository.save( replyDto.toEntity());
        replyEntity.setMemberEntity(memberEntity); // 리플엔티티에 멤버엔티티 연결
        memberEntity.getReplyEntityList().add(replyEntity);//멤버엔티티에 리플엔티티 연결

        System.out.println( "ReplyService replyEntity.toString 확인하기"+ replyEntity.toString() );

        return true;


    }

}
