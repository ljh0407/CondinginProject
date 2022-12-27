import React , { useState , useEffect } from "react";
import {useParams} from "react-router-dom";
import axios from "axios";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";


export default function Letterlist(porps){

    //---------------------------[쪽지보내기]----------------------------------//


    const [show, setShow] = useState(false);    // 닫기
    const handleClose = () => setShow(false);   // 쪽지 보내기 버튼
    const handleShow = () => setShow(true);     // 닫기 버튼

    //---------------------------[쪽지보내기]----------------------------------//


    const [ LetterList , setLetterList ] = useState( [])

    function getletter()  {
        axios
            .get("/letter/fromletter")
            .then( re => {
                console.log( '쪽지리스트 : '+re.data );
                setLetterList(re.data);
            })
            .catch(err => {console.log('리스트 오류'+err);})
    }

    const loadView=()=>{
        axios
            .get("/letter/lview")
            .then(res=>{setLetterList(res.data); console.log(res.data)})
            .catch(err=>console.error(err))
        window.location = "/letter/lview"
    }

    useEffect(getletter , [] );

    return(
        <div>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>쪽지 보기</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>보낸 사람</Form.Label>
                            <Form.Control
                                type="email"
                                placeholder="name@example.com"
                                Value={LetterList.memail}
                                className="lfrom"
                                autoFocus
                                disabled    //아이디 고정(내용 못고침)
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlTextarea1"
                        >
                            <Form.Label>내용</Form.Label>
                            <Form.Control as="textarea" rows={6} className="lcontent" />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}> 닫기 </Button>
                </Modal.Footer>
            </Modal>    {/*모달 - 쪽지보내기 end */}
            <h3> 보낸 쪽지 함 </h3>
            <table>
                {
                    LetterList.map( (l) => {
                return(
                    <tr>
                        <td variant="primary" onClick={handleShow} >{l.lto}</td>
                        <td>{l.lcontent}</td>
                    </tr>
                )

            })
                }
            </table>
    </div>
    )
}