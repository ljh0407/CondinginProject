package codingin.service;

import codingin.domain.dto.BoardDto;
import codingin.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        return boardEntity.getBno();
    }

    // 2. 글 출력하기 12.5 최예은
    public List<BoardDto> blist(){
        List<BoardEntity> elist = boardRepository.findAll(); //모든엔티티를 꺼내온다
        List<BoardDto> dlist = new ArrayList<>(); //js는 엔티티를 모르니 dto로 변환 그릇을 미리 만들어 둔다 아직은 깡통이다
        for(BoardEntity entity : elist){ // 원본에 있는 그릇을 하나씩 dto   그릇에 담아준다.
            dlist.add(entity.toDto()); //아까 만든 깡통에 하나씩 담아준다.
        }
        return  dlist;
    }

    // 3. 개별  글 보기

    //4. 모든 글 출력하기

    //5. 글 수정하기

    //6. 글 삭제하기



}
