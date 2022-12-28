import React , { useState , useEffect } from 'react';
import axios from "axios";
import {useParams} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css';
//12.24 최예은 css추가함
import css from '../../css/Board/bview.css'

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';

//조회수 아이콘 이미지 추가 12.26 최예은
import bviewImg from '../../img/bviewImg.png'

import Rereply from './Rereply'

let replyContent = ''; // 댓글내용

export default function Bview(props){   //상세보기
    //---------------------------[쪽지보내기]----------------------------------//
    const [ lto , setLto ] = useState( [] );    //받는사람
    const [ lfrom , setLfrom ] = useState( [] );    // 보내는사람
    const [show, setShow] = useState(false);    // 닫기

    const handleClose = () => setShow(false);   // 쪽지 보내기 버튼
    const handleShow = () => setShow(true);     // 닫기 버튼

    const handleSend = () => {
        // 1. 받는사람 , 내용
        let lcontent = document.querySelector(".lcontent").value;
        axios//post의 url                        받는사람의 이메일(글작성자) 내용
            .post("/letter/setletter" , { lto : board.memail , lcontent : lcontent  })
            .then( re => {
                if( re.data == true ){alert("쪽지보내기 성공");
                    console.log(re.data);}
                else { alert("쪽지보내기 실패");}
            })
            .catch( err => {console.log(err)})
        setShow(false); // model close
    }   // 쪽지 보내기 버튼 end
    //---------------------------[쪽지보내기]----------------------------------//

    //---------------------------[글상세보기]----------------------------------//
    const params = useParams();  //경로[URL]상의 매개변수 가져올때
    //게시물 , 리랜더링될 게시물           // 게시물 메모리
    const [ board , setBoard ] = useState({ });

    //좋아요버튼
    const [goodBtn,setgoodBtn] = useState([]);
    //싫어요버튼
    const [badBtn,setbadBtn] = useState([]);
    //댓글
    const [ReplyDto,setReplyDto] = useState([]); //  함수와 이름이 동일x 12.27 변경함
    //대댓글
    const [RereplyDto,setRereplyDto] = useState([]);

    //---------------------------[댓글]----------------------------------//
    useEffect( // 1. 서버로 부터 해당 게시물번호의 시물정보 요청
    () => axios
        //컨트롤 목록조회 url                   선택한 게시물의 bno 받기
        .get("/board/getbview" , { params : {bno : params.bno}})
        //setBoard(리랜더링)에 데이터 담기
        .then( res => {setBoard(res.data); getrdplelist();  console.log(res.data) }) ,[]);

    //로그인 맞는지 확인
    const [ login , setLogin ] = useState([]); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
      useEffect( // 1. 서버로 부터 해당 게시물번호의 시물정보 요청
        () =>  axios
          .get("/member/getloginMno") //url 호출
          .then( (response) => { setLogin( response.data );  console.log( login ) } ) ,[]);

    // 2. 해당 게시물번호의 해당하는 업데이트 페이지로 이동
    const getUpdate = () => { alert('수정'); window.location.href='/board/update/'+params.bno;  }

    //3. 게시물 삭제하기
    const onDelete = () =>{
        axios
            .delete("/board/delboard", { params:{bno:params.bno} } )
            .then(res => {alert("게시물삭제성공"); window.location.href="/"})
    }

    //4.좋아요 클릭
    const bgoodBtn = ()=>{
        //alert("좋아요")
        axios
            .get("/board/good",{params:{bno:params.bno}})
            .then(res => {setgoodBtn(res.data);})
            .catch(err=>{console.log(err);})
    }

    //5. 싫어요 클릭
    const bbadBtn = ()=>{
        //alert("싫어요")
        axios
            .get("/board/bad",{params:{bno:params.bno}})
            .then(res => {setbadBtn(res.data);})
            .catch(err=>{console.log(err);})
    }
    //6.댓글
    const setreply = ()=>{
            //alert("댓글댓글")
            let info = {
                bno : params.bno ,
                rcomment : document.querySelector(".replyContent").value
            }

            console.log( info  )

            axios.post("/reply/setreply", info )
                .then(res => {
                    //console.log( res );
                    //console.log( res.data );
                    if(res.data == true){ alert("댓글등록이 완료되었습니다."); getrdplelist(); }
                    else{ alert("댓글등록 실패") }
                })
                .catch(err => {console.log(err)});
    }

    //7.댓글 출력하기
    function getrdplelist(){
        axios
            .get("/reply/getrdplelist",{params:{bno:params.bno}})
            .then(res=>{
                console.log(res.data);
                setReplyDto( res.data );
                })
            .catch(err => {console.log(err)});
    }

    //8.댓글 삭제하기
    function replyDelete( rno ){
        axios
            .delete("/reply/deletereply" , { params : { "rno" : rno }})
            .then(res => {
                if(res.data == true){ alert("댓글삭제 성공") }
                else{alert("댓글삭제 실패")}
            } )
            .catch(err => { console.log(err)})
    }


    //9.대댓글 작성하기
    function setrerply (rno,i) {
        alert("대댓글")
        alert(rno)
        let rercomments =  document.querySelectorAll(".rercomment") //  .rercomment class명 모두  배열로 가져오기
        let data={
            "rno" : rno ,
            rercomment : rercomments.item(i).value // 해당 선택한 i 번째 .rercomment의 입력된 데이터 호출
        }
        console.log( i )
        console.log( data );
        //console.log(data)
       axios
           .post("/reply/setrerply" ,data )
           .then(res=>{
                if(res.data==true){ alert("대댓글 작성 성공");}
                else{ alert("대댓글 작성 실패")}
            })
    }

    //10.대댓글 출력하기
//
//    function getrereplylist(rno){
//        axios
//            .get("/reply/getrereplylist",{params:{"rno":rno}})
//            .then(res=>{
//                console.log(res.data)
//                console.log(res)
//                setRereplyDto(res.data)
//            })
//    }

//댓글쓰기를 클릭하면 대댓글창이 나오는 함수
function rereplyWrite(){
    alert("dd")
}

    //---------------------------[글상세보기]----------------------------------//
    return(
        <div>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>쪽지 보내기</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>받는사람</Form.Label>
                            <Form.Control
                                type="email"
                                placeholder="name@example.com"
                                Value={board.memail}
                                className="lto"
                                autoFocus
                                disabled
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlTextarea1"
                        >
                            <Form.Label>쪽지내용</Form.Label>
                            <Form.Control as="textarea" rows={6} className="lcontent" />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}> 닫기 </Button>
                    <Button variant="primary" onClick={handleSend}> 보내기 </Button>
                </Modal.Footer>
            </Modal>    {/*모달 - 쪽지보내기 end */}



            {/*///////////////////////////////글보기 영역/////////////////////////////////////*/}

            <div class="wrap">{/*전체영역입니다.*/}
                <div> {/*여기에 카테고리 이름을 넣고 싶어요 cname*/}</div>
                {/*프로필 영역 및 조회수 영역*/}
                <div className="memberWrap">

                    {/*프로필사진출력*/}
                    <img className="mprofileImg" src={"/static/media/"+ board.mprofile } />

                    <div className="memberInforSection">
                        <div className="memail">
                            <span  variant="primary" onClick={handleShow} > {board.memail}</span>{/*작성자*/}
                        </div>
                        <div className="dateNbviewSection">{/*작성시간 및 조회수*/}
                            <span className="bdate">{board.bdate} {/*작성시간*/}</span>
                            <span className="bview">{board.bview} {/*조회수*/}</span>
                        </div>
                    </div>{/*memberInforSection*/}
                </div>{/*memberWrap*/}

                <h2 className="btitle">{board.btitle}</h2>{/*제목*/}

                {/*내용영역입니다.*/}
                <div dangerouslySetInnerHTML={{__html:board.bcontent }} className="bcontent"></div>  {/*dangerouslySetInnerHTML={{__html:board.bcontent }} p태그 제거 html형식으로 뿌리기*/}

                <div className="goodNbadSection">{/*좋아요 및 싫어요 영역*/}
                    <div className="goodMbadBtn">
                        <span className="icon goodIcon">아이콘{/*좋아요 아이콘이 들어갈 예정입니다.*/}</span>
                        <span onClick={bgoodBtn} className="bgood"> {board.bgood}  </span>
                    </div>
                    <div className="goodMbadBtn">
                        <span className="icon badIcon">아이콘{/*싫어요 아이콘이 들어갈 예정입니다.*/}</span>
                        <span onClick={bbadBtn} className="bbad">{board.bbad} </span>
                    </div>
                </div>{/*goodNbadSection*/}

                {/* 작성자와 로그인이 같으면 버튼 노출*/}
                <div className="btnSection">
                    { login==board.memail && <button type="button" onClick={onDelete} className="deleteBtn btn">삭제</button> }
                    { login == board.memail && <button type="button" onClick={ getUpdate } className="updateBtn btn"> 수정 </button>  }
                </div>

                {/*////////////////////////////////댓글영역입니다.////////////////////////////////////////*/}

                {/*댓글 컴포넌트 넣기*/}

                <form className="repleWrap">
                    <div className="repleSection">
                        {/*<img className="repleProfile" src={"/static/media/"+ login.mprofile } />*/}

                        <img className="repleProfile" src={"/static/media/"+ login.mprofile } />{/*댓글작성자의 프로필 사진입니다.*/}
                        <textarea
                            className="replyContent"
                            placeholder = {  login == '' ? "로그인후 댓글 작성가능합니다." : "생각의 차이를 인정하고 공감해 주세요."  }
                            >
                        </textarea>{/*댓글내용입니다.*/}
                    </div>
                    <div className="repleBtnSection">
                        <button type="button" className="relpleBtn" onClick={ setreply }>작성하기</button>    {/*함수실행*/}
                    </div>
                </form>{/*repleWrap*/}



                <div className="getRepleylist">
                    {/*여기에 댓글이 출력이 될 예정입니다.*/}
                {
                    ReplyDto.map((r , i)=>{
                        return(
                            <>
                            <div className="memberProfileImg">
                                <img className="profile" src={"/static/media/"+ r.bfilename } />{/*댓글을 작성한 사람의 프로필*/}
                            </div>

                            <div>
                                <span>{r.bdate}</span>
                            </div>

                            <div>{r.rcomment}</div>
                            <div>rno : {r.rno}</div>
                            { (r.memail === login.memail && (<button type="button" onClick={  ()=>replyDelete( r.rno ) }> 댓글 삭제하기 </button>) )    }
                            <div className="rereplyWrite"> <span onClick={rereplyWrite}>댓글쓰기</span>

                                <div className="RereplyBox">
                                    <textarea
                                        className="rercomment"
                                        placeholder={login ==''? "로그인 후 댓글 작성이 가능합니다." : "생각의 차이를 인정하고 공감해 주세요."}
                                        >
                                    </textarea>{/*대댓글 입력하는 공간*/}
                                    <button type="button" onClick={ (  )=> setrerply( r.rno , i ) }>작성하기</button>
                                </div>{/*RereplyBox*/}

                            </div>{/*rereplyWrite*/}



                            {/*//////////////////////////////////////대댓글출력공간/////////////////////////////////////*/}

                                <Rereply data = { r.rereplyDtos } />{/*중첩이 되지 않아 Rereply파일일을 만들었습니다.*/}

                            </>
                        )
                    })
                }

                </div>{/*repleSection*/}

            </div>{/*wrap*/}


        </div>

    )
}

   /*<div variant="primary" onClick={handleShow} >{board.memail}</div>*/