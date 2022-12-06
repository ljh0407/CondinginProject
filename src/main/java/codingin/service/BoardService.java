package codingin.service;

import codingin.domain.dto.BoardDto;
import codingin.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    //=============전역변수=============================//
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LetterRepository letterRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private RereplyRepository rereplyRepository;
    //====================================================//



    //1. 개별글쓰기 12.5 최예은
    @Transactional
    public int bwrite(BoardDto boardDto){
        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity());
            System.out.println("BoardService 1. 개별 글 쓰기 boardEntity 확인 : " + boardEntity);
        return boardEntity.getBno();
    }

    // 2. 글 출력하기 12.5 최예은
    public List<BoardDto> blist(){
        List<BoardEntity> elist = boardRepository.findAll(); //모든엔티티를 꺼내온다
            System.out.println("BoardService 2. 글 출력하기 elist 확인 : " + elist ); //확인하기
        List<BoardDto> dlist = new ArrayList<>(); //js는 엔티티를 모르니 dto로 변환 그릇을 미리 만들어 둔다 아직은 깡통이다
        for(BoardEntity entity : elist){ // 원본에 있는 그릇을 하나씩 dto   그릇에 담아준다.
            dlist.add(entity.toDto()); //아까 만든 깡통에 하나씩 담아준다.
        }
            System.out.println("BoardService 2. 글 출력하기 dlist 확인 : " + dlist); // 확인하기
        return  dlist;
    }

    // 3. 개별  글 보기 12.6 최예은
    @Transactional
    public BoardDto getboard(int bno){
        //1.입력받은 게시물 번호로 엔티티검색
        Optional<BoardEntity> optional = boardRepository.findById(bno);
            System.out.println("boardservice 3.개별글 보기 optional 확인" + optional);
        //2.optional안에 있는 내용물을 확인한다
        if(optional.isPresent()){ // 내용물이 있는지 확인
            //optional에 있는 객체 하나씩 꺼내서 entity에 넣기
            BoardEntity boardEntity = optional.get();
                System.out.println("BoardService 3.개별 글 보기 boardEntity 확인  : " + boardEntity);
                System.out.println("BoardService 3.개별 글 보기 boardEntity.toDto 확인  : " + boardEntity.toDto());
            return boardEntity.toDto(); //형변환된 dto 반환
        }//if end
        else {
            return null; //없으면 null 반환
        }
    }


    //4. 글 수정하기 12.6 최예은
    public boolean upboard(BoardDto boardDto){
            System.out.println("Boardservice 5. 글 수정하기 boardDto toString 확인 : " + boardDto.toString());
        //1.dto에서 수정할 pk번호 이용하여 엔티티 찾기
        //글 수정은 글을 작성한 사람만 가능하니까 뭔가 추가를 해야 할 것 같은데? 아닌가 어차피 수정삭제는 본인만 볼 수 있으니까 ..?
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
            System.out.println("Boardservice 5. 글 수정하기 optional 확인 : " + optional);
        if(optional.isPresent()) {// 내용물이 있는지 확인
            BoardEntity entity = optional.get();
                entity.setBtitle(boardDto.getBtitle()); //글제목수정
                entity.setBcontent(boardDto.getBcontent()); // 글 내용수정
                entity.setBfile(boardDto.getBfile()); // 파일 수정하기
                return true;
        }//if end
        else{
            return false;
        }
    }


    //5. 글 삭제하기 12.6 최예은
    public boolean deleteboard( int bno){
        boardRepository.findById(bno);
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        System.out.println("Boardservice 6.글 삭제하기 optional 확인 : " + optional);
        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
                System.out.println("Boardservice 6.글 삭제하기 boardEntity 확인 : " + boardEntity);
            boardRepository.delete(boardEntity);
            return true;
        }
        else{
            return false;
        }
    }



}
//12.6
/*
글 수정 및 삭제는 작성한 사람만 볼 있고, 작성한 사람만 수정 및 삭제가 가능한데

서비스에서 조작을 해야하는건가? 아니면 스크립트에서? jsp 보니까 스크립트에서 조작한 것 같은데
로그인 한 사람과 작성한 사람이 같으면? 이런식으로...
수정,삭제버튼을 보이게 하고 그렇지 않으면 그냥 작성할 글만 볼 수 있도록 해야하는건가?
근데 수정할때 제목이랑 내용이랑 파일 수정하는건데,
*/