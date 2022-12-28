package codingin.service;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.MemberDto;
import codingin.domain.dto.ReplyDto;
import codingin.domain.dto.RereplyDto;
import codingin.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service    //댓글 고은시 12.22
public class ReplyService {
    //====================================================//
    @Autowired  //댓글
    private ReplyRepository replyRepository;
    @Autowired  //대댓글
    private RereplyRepository rereplyRepository;
    @Autowired // 12.26 최예은 추가함
    private  BoardRepository boardRepository;
    
    @Autowired // 12.27 최예은 추가함
    private MemberRepository memberRepository;
    @Autowired  //멤버서비스
    private MemberService memberService;
    @Autowired  //보드서비스
    private BoardService boardService;



    //====================================================//

    //12.26 댓글 등록하기

    @Transactional
    public boolean setreply(ReplyDto replyDto ){
        BoardDto boardDto = boardService.getboard( replyDto.getBno() ); // 상세보기 함수에서 bno 가져오기
        System.out.println( "ReplyService boardDto 확인 : " + boardDto.toString());
        System.out.println( "ReplyService replyDto 확인 : " + replyDto.toString() );

        // dto entity로 형변환
        //로그인을 한 경우에만 댓글 작성이 가능함 그러면 로그인을 했는지도 확인을 해야함.Mno가져와야함
        MemberEntity memberEntity = memberService.getEntity();
        System.out.println("ReplyService memberEntity 확인 : "+memberEntity);
        if(memberEntity == null){return  false;} // 만약에 로그인을 하지 않으면 리턴
        //boardDto bno 가져오기
        BoardEntity boardEntity = boardRepository.findById(boardDto.getBno()).get();
        //System.out.println("repleyService boardRepository 확인 : "+ boardEntity.getBno());
        ReplyEntity replyEntity = replyRepository.save( replyDto.toEntity());
        //System.out.println("repleyService  replyEntity : "+replyEntity);
        if(replyEntity.getRno()!=0){//댓글 번호가 0이 아니면
            
            replyEntity.setMemberEntity(memberEntity); // replyEntity에 memberEntity 연결
            memberEntity.getReplyEntityList().add(replyEntity);//memberEntity에 replyEntity 연결

            boardEntity.getReplyEntityList().add(replyEntity);//boardEntity에 RepleEntityList 저장
            replyEntity.setBoardEntity(boardEntity);//replyEntity에 boardEntity 추가

            //System.out.println( "ReplyService replyEntity.toString 확인하기 : "+ replyEntity.toString() );
            return true;
        }else{ return  false;}
    }//댓글 등록하기 end

    //2. 댓글 출력하기 12.26 최예은
    @Transactional
    public List<ReplyDto> getrdplelist( int bno ){ //  어떤글인지 판단을 하기 위해서
        List<ReplyEntity> rentitylist = boardRepository.findById( bno ).get().getReplyEntityList(); //replyEntity에 있는 리스트들을 가져온다
        //System.out.println("ReplyService 2. 댓글 출력하기 rist 확인: " + rentitylist); // 확인한번 해본다
        //dto 를 담을 깡통하나 만들어 준다.
        List<ReplyDto> rdto = new ArrayList<ReplyDto>();//컨트롤 에게 전달하기 위한 형변환 깡통 그릇
        for(ReplyEntity rentity : rentitylist){
            rdto.add(rentity.toDto());//??????????????
        }

        return rdto;
    }


    //3. 댓글 삭제하기

    @Transactional
    public boolean deletereply(int rno ){
        replyRepository.findById(rno);
        Optional<ReplyEntity> optional = replyRepository.findById(rno);
        if(optional.isPresent()){
            ReplyEntity replyEntity = optional.get();
            replyRepository.delete(replyEntity);
            return true;
        }else{ return false; }
    }

    //4. 대댓글 작성하기
    @Transactional
    public boolean setrereply(RereplyDto rereplyDto){
        MemberEntity memberEntity = memberService.getEntity();
        System.out.println("ReplyService memberEntity 확인하기 : " + memberEntity.getMno());
        if(memberEntity == null){return false;}//만약에 로그인을 하지 않았으면


        ReplyEntity replyEntity = replyRepository.findById(rereplyDto.getRno()).get();
            System.out.println("ReplyService replyEntity 확인 : "+ replyEntity);
        RereplyEntity rereplyEntity = rereplyRepository.save(rereplyDto.toEntity());
        System.out.println("ReplyService rereplyEntity 확인 : " + rereplyEntity);

            rereplyEntity.setMemberEntity(memberEntity);
            memberEntity.getRereplyEntityList().add(rereplyEntity);

            replyEntity.getRereplyEntityList().add(rereplyEntity);
            rereplyEntity.setReplyEntity(replyEntity);
            //Rereply.jsx & reply
            return true;
    }
    
    //5.대댓글 출력하기
//    @Transactional
//    public List<RereplyDto> getrereplylist( int rno ){
//        //모든 대댓글을 가져온다
//        List<RereplyEntity> rerplylist = replyRepository.findById(rno).get().getRereplyEntityList(); //모든 리스트 들을 가져온다.
//            System.out.println("ReplyService 5.대댓글 출력하기 : " + rerplylist);
//        List<RereplyDto> rerplydto = new ArrayList<>();
//        for(RereplyEntity rereplyEntity : rerplylist){
//            rerplydto.add(rereplyEntity.toDto());
//        }
//        System.out.println("ReplyService 5.대댓글 출력하기 :  " + rerplydto);
//
//        return rerplydto;
//    }
    //6.대댓글 삭제하기
    @Transactional
    public boolean deleterereply(int rno ){
        rereplyRepository.findById(rno);
        Optional<RereplyEntity> optional = rereplyRepository.findById(rno);
        if(optional.isPresent()){
            RereplyEntity rereplyEntity = optional.get();
            rereplyRepository.delete(rereplyEntity); //엔티티 조작
            return true;
        }else{ return false;}
    }

}