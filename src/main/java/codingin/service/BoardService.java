package codingin.service;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.PageDto;
import codingin.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private  UpdownRepository updownRepository;

    //12.14최예은 memberService 추가함
    @Autowired
    private MemberService memberService;
    //====================================================//
    //1. 개별글쓰기 12.5 최예은
/*    @Transactional
    public int bwrite(BoardDto boardDto){
        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity());
        System.out.println("BoardService 1. 개별 글 쓰기 boardEntity 확인 : " + boardEntity);
        return boardEntity.getBno();
    }*/
    //12.14 1.글 쓰기 최예은
    @Transactional
    public boolean setboard( BoardDto boardDto){
        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity());
        System.out.println("BoardService 1. 개별 글 쓰기 boardEntity 확인 : " + boardEntity);
        return true;

    }

    // 2. 글 출력하기 12.14 최예은
    // page : 현재 페이지번호 , key : 검색필드명 , keyword : 검색 데이터
    @Transactional
    public PageDto blist(PageDto pageDto){
        Page<BoardEntity> elist = null; //먼저 선언함
        Pageable pageable = PageRequest.of(pageDto.getPage()-1,2,Sort.by(Sort.Direction.DESC,"bno")) ; //페이징설정
        //PageRequest.of(현재페이지번호, 표시할레코드수,정렬)
        System.out.println("BoardService 2. 글목록보기 pageable 확인 "+ pageable);
        //검색여부
        elist = boardRepository.findbySearch(pageDto.getKey(), pageDto.getKeyword(),pageable);
        //프론트엔드에 표시할 페이징번호 버튼 수
        System.out.println("BoardService 2. 글출력하기 elist 확인"  + elist);

        int btncount = 5;
        int startbtn = (pageDto.getPage()/btncount)*btncount+1;// 시작번호 버튼
        int endbtn = startbtn+btncount-1; //끝번호

        if(endbtn>elist.getTotalPages()) endbtn = elist.getTotalPages();

        List<BoardDto> dlist = new ArrayList<BoardDto>();//컨트롤에게 전달할 때 형변한 하기 위한 그릇

        for(BoardEntity entity : elist){//반환
            dlist.add(entity.toDto());
            System.out.println("BoardService 2. 글출력하기 dlist 확인하기 :" + dlist);
        }
        pageDto.setList(dlist); //글작성
        pageDto.setStartbtn(startbtn); //시작버튼
        pageDto.setEndbtn(endbtn); //끝버튼
        pageDto.setTotalBoards(elist.getTotalElements());
        System.out.println("BoardService 2. 글출력하기 pagedto 확인 : " + pageDto);
        return pageDto;
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

            BoardDto boardDto = boardEntity.toDto(); // board객체
            //12.16 고은시 보드디티오에 있는 bwrite삭제하면서 일단 자료형만 맟춰서 수정
            if(boardDto.getBtitle().equals("qweqwe")){//만약에 로그인한 사람과 글을 작성한 사람과 일치하면 "qwe"는 로그인세션 나중에 집어넣어주기
                boardDto.setBtnaction(true); //있으면 수정삭제 버튼 보여주기
            }else {
                boardDto.setBtnaction(false); //그렇지 않으면 그냥 화면 보여주기
            }

            return boardDto; //형변환된 dto 반환
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


}// class end

