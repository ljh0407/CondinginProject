import React , { useState , useEffect } from "react";
import axios from "axios";

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import {useParams} from "react-router-dom";
export default function ToLetter(porps){

    const params = useParams();  //경로[URL]상의 매개변수 가져올때

    //---------------------------[쪽지보내기]----------------------------------//

    const [show, setShow] = useState(false);    // 닫기
    const handleClose = () => setShow(false);   // 쪽지 보내기 버튼
    const handleShow = () => setShow(true);     // 닫기 버튼

    //---------------------------[쪽지보내기]----------------------------------//

    const [ toletter , setTolist ] = useState( [])

    useEffect( // 1. 서버로 부터 해당 게시물번호의 시물정보 요청
        () => {
            console.log('ddddddd')
            axios
            //컨트롤 목록조회 url                   선택한 게시물의 bno 받기
            .get("/letter/lview" , { params : {lno : params.lno}})
            .then( res => {setTolist(res.data); console.log(res.data) })
            .catch(err => {console.log('쪽지 상세보기오류 : ' + err)})
        } ,[]);


    const [ LetterList2 , setLetterList2 ] = useState( [])

    function getletter2()  {
        axios
            .get("/letter/toletter")
            .then( re => {
                console.log( '쪽지리스트 : '+re.data );
                setLetterList2(re.data);
            })
            .catch(err => {console.log('리스트 오류'+err);})
    }

    useEffect(getletter2 , [] );
    // 받은 쪽지 리스트
    return(
        <div>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title> 쪽지 보기 </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>받은 사람</Form.Label>
                            <Form.Control
                                type="email"
                                placeholder="name@example.com"
                                Value={ toletter.memail }
                                className="lto"
                                autoFocus
                                disabled    //아이디 고정(내용 못고침)
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlTextarea1"
                        >
                            <Form.Label>내용</Form.Label>
                            <Form.Control rows={6} className="lcontent"
                                          Value={ toletter.lcontent }
                            />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={ handleClose }> 닫기 </Button>
                </Modal.Footer>
            </Modal>    {/*모달 - 쪽지보내기 end */}

            <h3> 받은 쪽지 함 </h3>
            <table>
                {
                    LetterList2.map( (l) => {
                        return(
                            <tr>
                                <td variant="primary" onClick={ handleShow } >{ l.lto }</td>
                                <td>{ l.lcontent }</td>
                            </tr>
                        )
                    })
                }
            </table>
        </div>
    )
}