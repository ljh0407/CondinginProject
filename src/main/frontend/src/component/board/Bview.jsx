import React , { useState , useEffect } from 'react';
import axios from "axios";
import {useParams} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css';


import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';


let lcontent = '';
export default function Bview(props){

    const [ lto , setLto ] = useState( [] );

    const [ lfrom , setLfrom ] = useState( [] );

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);   // 쪽지 보내기 버튼
    const handleShow = () => setShow(true);     // 닫기 버튼

    const handleSend = () => {

        // 1. 받는사람 , 내용
        console.log( board.bno  )

        console.log( document.querySelector(".lcontent").value  )
        axios
            .post("/letter/setletter" , { lto : board.memail , lcontent : lcontent  })
            .then( re => {
                if( re.data == true ){alert("쪽지보내기 성공");
                    console.log(re.data);}
                else { alert("쪽지보내기 실패");}
            })
            .catch( err => {console.log(err)})
        setShow(false); // model close
    }   // 쪽지 보내기 버튼





    /*12.16 게시물 상세보기*/
    const params = useParams();  // useParams() 훅 : 경로[URL]상의 매개변수 가져올때
    //게시물 , 리랜더링될 게시물           // 게시물 메모리
    const [ board , setBoard ] = useState({ });

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
            .delete("/board/delboard",{params:{bno:params.bno}})
            .then(res => {alert("게시물삭제성공"); window.location.href="/board/boardlist"})
    }

    // 4. 쪽지 보내기


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
                    <Button variant="secondary" onClick={handleClose}>
                        닫기
                    </Button>
                    <Button variant="primary" onClick={handleSend}>
                        보내기
                    </Button>
                </Modal.Footer>
            </Modal>


            <div>{board.btitle}</div>
            <div variant="primary" onClick={handleShow} >{board.memail}</div>
            <div>{board.bcontent}</div>
            <div>{board.bdate}</div>
            <div>{board.bview}</div>
            <div>{board.bgood}</div>
            <div>{board.bbad}</div>
            <div>{board.mprofile}</div>

            {/*12.16 작성자와 로그인이 같으면 버튼 노출*/}
            { login==board.memail && <button type="button" onClick={onDelete}>삭제</button> }
            { login == board.memail && <button type="button" onClick={ getUpdate }> 수정 </button>  }
        </div>

    )
}