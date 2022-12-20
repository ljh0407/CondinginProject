package codingin.service;

import codingin.domain.dto.BoardDto;
import codingin.domain.dto.CategoryDto;
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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    //12.15 최예은 추가
    @Autowired
    private HttpServletRequest request; // 요청객체선언
    //12.15 최예은 추가


    @Autowired
    private HttpServletResponse response; //응답객체 선언
    // 첨부파일 경로



    String path = "C:\\upload\\";  // C드라이브-> upload 폴더 생성

    //====================================================//
   // 0. 첨부파일 다운로드
    public void filedownload( String filename ){
        String realfilename ="";  // uuid 제거  //
        String [] split = filename.split("_"); // 1. _ 기준으로 자르기
        for( int i = 1 ; i<split.length ; i++ ) { // 2. uuid 제외한 반복문 돌리기
            realfilename += split[i];               // 3. 뒷자리 문자열 추가
            if (split.length-1 != i ){      // 마지막 인덱스 아니면
                realfilename += "_";        // 문자열[1] _ 문자열[2] _ 문자열[3].확장자명
            }
        }
        String filepath = path+filename; // 1. 경로 찾기
        try {  // 2. 헤더 구성 [ HTTP 해서 지원하는 다운로드형식 메소드 [ response ]
            response.setHeader( // 응답
                    "Content-Disposition", // 다운로드 형식 [ 브라우저 마다 다름 ]
                    "attachment;filename=" + URLEncoder.encode(realfilename, "UTF-8")); // 다운로드에 표시될 파일명
            File file = new File(filepath); // 해당 경로의 파일 객체화
            // 3. 다운로드 스트림 [ ]
            BufferedInputStream fin = new BufferedInputStream( new FileInputStream(file)  ); // 1. 입력 스트림 객체 선언
            byte[] bytes = new byte[ (int)file.length() ];  // 2. 파일의 길이만큼 배열 선언
            fin.read( bytes );      // * 스트림 읽기 [ 대상 : new FileInputStream(file) ] // 3. 파일의 길이만큼 읽어와서 바이트를 배열에 저장
            BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream() ); // 4. 출력 스트림 객체 선언
            fout.write( bytes );    // * 스트림 내보내기   [ response.getOutputStream() ]  // 5. 응답하기 [ 배열 내보내기]
            fout.flush(); fout.close(); fin.close();  // 6. 버퍼 초기화 혹은 스트림 닫기

        }catch(Exception e){ System.out.println(e);  }
    }

    // * 첨부파일 업로드 [ 1. 쓰기메소드 2. 수정메소드 ] 사용
    @Transactional              //  boardDto : 쓰기,수정 대상     BoardEntity:원본
    public boolean fileupload( BoardDto boardDto , BoardEntity boardEntity ){
        if( !boardDto.getBfile().getOriginalFilename().equals("") ) { // ** 첨부파일 있을때
            // * 업로드 된 파일의 이름 [ 문제점 : 파일명 중복 ]
            String uuid = UUID.randomUUID().toString(); // 1. 난수생성
            String filename = uuid + "_" + boardDto.getBfile().getOriginalFilename(); // 2. 난수+파일명
            // * 첨부파일명 db 에 등록
            boardEntity.setBfile(filename); // 해당 파일명 엔티티에 저장 // 3. 난수+파일명 엔티티 에 저장
            // * 첨부파일 업로드 // 3. 저장할 경로 [ 전역변수 ]
            try {
                File uploadfile = new File(path + filename);  // 4. 경로+파일명 [ 객체화 ]
                boardDto.getBfile().transferTo(uploadfile);   // 5. 해당 객체 경로 로 업로드
            } catch (Exception e) {
                System.out.println("첨부파일 업로드 실패 ");
            }
            return  true;
        }else{ return  false;}
    }

    //1. 개별글쓰기 12.5 최예은
    //12.14 1.글 쓰기 최예은
    @Transactional
    public boolean setboard( BoardDto boardDto){
        MemberEntity memberEntity = memberService.getEntity();  //멤버서비스에서 작성한 메소드 호출
        if(memberEntity == null){return false;} //만약에 멤버엔티티가 null(회원정보가 없으면)실패
        //dto -> entity에 담기
        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity());

        if(boardEntity.getBno() != 0){  //게시물 번호가 0이 아니면
            boardEntity.setMemberEntity(memberEntity);  //보트엔티티에 멤버엔티티 연결
            return true;    //게시물번호가 0이 아니면 저장
        }else {return false;}   //게시물번호가 0이면 실패
    }

    // 2. 글 출력하기 12.14 최예은
    // page : 현재 페이지번호 , key : 검색필드명 , keyword : 검색 데이터
    @Transactional
    public PageDto getboardlist(PageDto pageDto){
        Page<BoardEntity> elist = null; //게시물 먼저 선언함
                                            //사용자 기준으로 1을 입력해서 -1해주기 표시 게시물수 2 , 내림차순(bno기준)
        Pageable pageable = PageRequest.of(pageDto.getPage()-1,5,Sort.by(Sort.Direction.DESC,"bno")) ; //페이징설정
                        //PageRequest.of(현재페이지번호, 표시할레코드수,정렬)
        //검색여부
        elist = boardRepository.findbySearch(pageDto.getKey(), pageDto.getKeyword(),pageable);
        //프론트엔드에 표시할 페이징번호 버튼 수
        List<BoardDto> dlist = new ArrayList<BoardDto>();//컨트롤에게 전달할 때 형변한 하기 위한 그릇
       for(BoardEntity entity : elist){//반환
           dlist.add(entity.toDto()); }
       //리액트 전달
       pageDto.setList(dlist); // 게시물 리스트
       pageDto.setTotalBoards(elist.getTotalElements());    //전체 게시물 수
       return pageDto;
    }

    // 3. 개별  글 보기 12.6 최예은
    @Transactional
    public BoardDto getboard(int bno){
        //1.입력받은 게시물 번호로 엔티티검색
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        //2.optional안에 있는 내용물을 확인한다
        if(optional.isPresent()){ // 내용물이 있는지 확인
            //optional에 있는 객체 하나씩 꺼내서 entity에 넣기
            BoardEntity boardEntity = optional.get();
            BoardDto boardDto = boardEntity.toDto(); // board객체
            System.out.println("서비스"+boardDto.toString());
            return boardDto; //형변환된 dto 반환
        }//if end
        else {
            return null; //없으면 null 반환
        }
    }

    //4. 글 수정하기 12.6 최예은
    @Transactional
    public boolean bupboard(BoardDto boardDto){
        System.out.println("**수정***");
        //1.수정할 게시물 찾기
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            System.out.println("**수정***1 : "+boardEntity);
            boardEntity.setBtitle(boardDto.getBtitle());
            System.out.println("**수정***2 : "+boardEntity);
            boardEntity.setBcontent(boardDto.getBcontent());
            System.out.println("**수정***3 : "+boardEntity);
            return true;
        }
        else {return false;}
    }
    //5. 글 삭제하기 12.6 최예은
    public boolean deleteboard( int bno){
        boardRepository.findById(bno);
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            boardRepository.delete(boardEntity);
            return true;
        }
        else{
            return false;
        }
    }
    //////////////////////////////////////카테고리 출력하기///////////////////////////////////////////////
    //6. 카테고리 출력하기 최예은
    public List<CategoryDto> bcategoryList(){
        List<CategoryEntity> categorylist = categoryRepository.findAll();
        System.out.println("Boardservice 6.카테고리 출력하기 확인하기" + categorylist );
        List<CategoryDto> dtolist = new ArrayList<>();
        categorylist.forEach( e -> dtolist.add( e.toDto() ) );
        return dtolist;
    }
    /////////////////////////////////////////////////////////////////////////

    //7.각 카테고리의 최신 글 가져오기 12.19 최예은 추가
    public List<CategoryDto> getlimitdesc( int cno){
        //클릭한 카테고리에서 모든 글을 일단 가져온다
        //그러면 cno이랑 boardlist랑 같이 넘어가야 하는건가?
        //아니면 하나씩 가져와야하는건가? 컴퓨터는 한꺼번에 처리 못할 텐데?
        //가져와야하는건가? 요청해야하는건가?

        List<BoardEntity> elist = boardRepository.findAll();

        //깡통하나만든다
        List<BoardDto> blist = new ArrayList<>();

        //향상된 for문으로 담아서
        for(BoardEntity entity : elist){
            blist.add(entity.toDto());
        }
        //리턴
        return null;
    }//7 end




}// class end

//select * from board where bcno=1  ORDER BY bno=1 DESC limit 4 ;