import React , { useState , useEffect } from 'react';
import axios from "axios";
import {useParams} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css';

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';

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
    const [badBtn,setbadBtn] = useState([])

    useEffect( // 1. 서버로 부터 해당 게시물번호의 시물정보 요청
    () => axios
        //컨트롤 목록조회url                   bno받기
        .get("/board/getbview" , { params : {bno : params.bno}})
        //setBoard에 데이터 담기
        .then( res => {setBoard(res.data)}) ,[]);

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
            .then(res => {setgoodBtn(res.data); console.log(res.data)})
            .catch(err=>{console.log(err);})
    }

    //5. 싫어요 클릭
    const bbadBtn = ()=>{
        axios
            .get("/board/bad",{params:{bno:params.bno}})
            .then(res => {setbadBtn(res.data) ;console.log(res.data)})
            .catch(err=>{console.log(err);})
        //alert("싫어요")
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

            <div>{board.btitle}</div>
            <div variant="primary" onClick={handleShow} >{board.memail}</div>
            <div dangerouslySetInnerHTML={{__html:board.bcontent }}></div>  {/*dangerouslySetInnerHTML={{__html:board.bcontent }} p태그 제거 html형식으로 뿌리기*/}
            <div>{board.bdate} 작성시간</div>
            <div>{board.bview} 조회수</div>
            <div onClick={bgoodBtn}>{board.bgood} 좋아요</div>
            <div onClick={bbadBtn}>{board.bbad} 싫어요</div>
            <div>{board.mprofile} 프로필</div>

            {/* 작성자와 로그인이 같으면 버튼 노출*/}
            { login==board.memail && <button type="button" onClick={onDelete}>삭제</button> }
            { login == board.memail && <button type="button" onClick={ getUpdate }> 수정 </button>  }

            {/*댓글 컴포넌트 넣기*/}
        </div>

    )
}