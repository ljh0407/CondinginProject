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
    const [reply,setreply] = useState([]);


    useEffect( // 1. 서버로 부터 해당 게시물번호의 시물정보 요청
    () => axios
        //컨트롤 목록조회url                   bno받기
        .get("/board/getbview" , { params : {bno : params.bno}})
        //setBoard에 데이터 담기
        .then( res => {setBoard(res.data); console.log(res.data) }) ,[]);

    //로그인 맞는지 확인
    const [ login , setLogin ] = useState(null); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
    axios
        .get("/member/getloginMno") //url 호출                    언더바 기준으로 자르기(작성자와 로그인한 사람확인)
        .then( (response) => { setLogin( response.data.split("_")[0] );  console.log( login ) } )

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
    //6.댓글작성
    const reple = () => {
        alert("댓글댓글")
        let repleWrap = document.querySelector(".repleWrap")
        let formdata = new FormData(repleWrap)
         formdata.set("bno",params.bno)//bno를 추가
         formdata.set("replyContent",replyContent) //댓글내용도 같이 넘긴다.

        console.log(repleWrap)
        console.log(formdata)

        axios
            .post("/reple/setreply/",formdata , {headers: {'Content-Type': 'multipart/form-data'} })
            .then




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

                    <div className="mprofileImg">{board.mprofile} {/*프로필*/}</div>

                    <div className="memberInforSection">
                        <div className="memail">
                            <span  variant="primary" onClick={handleShow} > {board.memail}</span>{/*작성자*/}
                        </div>
                        <div className="dateNbviewSection">{/*작성시간 및 조회수*/}
                            <span className="bdateText Text">작성시간 : </span>
                            <span className="bdate">{board.bdate} {/*작성시간*/}</span>

                            <span className="bviewText Text">조회수 : </span>
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
                        <span className="repleProfile">{board.mprofile}</span>{/*댓글작성자의 프로필 사진입니다.*/}
                        <textarea className="replyContent"></textarea>{/*댓글내용입니다.*/}
                    </div>
                    <div className="repleBtnSection">
                        <button onClick={reple} className="relpleBtn">댓글작성하기</button>{/*댓글작성하기 버튼입니다.*/}
                    </div>
                </form>{/*repleWrap*/}



                <div className="repleSection">
                    {/*여기에 댓글이 출력이 될 예정입니다.*/}
                </div>{/*repleSection*/}

            </div>{/*wrap*/}

        </div>

    )
}

   /*<div variant="primary" onClick={handleShow} >{board.memail}</div>*/