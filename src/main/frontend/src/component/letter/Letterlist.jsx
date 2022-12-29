import React , { useState , useEffect } from "react";
import {useParams} from "react-router-dom";
import axios from "axios";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Pagination from "react-js-pagination";

import StyleSheet from '../../css/Letter/Letterlist.css'; // css 불러오기

export default function Letterlist(porps){

    //---------------------------[쪽지보내기]----------------------------------//


    const [ selectItem , setSelectItem ] = useState(0);

    const [show, setShow] = useState(false);    // 닫기

    const [page, setPage] = useState(1 );    // 페이지

    const handleClose = () => setShow(false);   // 쪽지닫기

    const handleShow = (i) => {
        setSelectItem(i);
        setShow(true);
    }     // 쪽지 열기

    //---------------------------[쪽지보내기]----------------------------------//


    const [ LetterList , setLetterList ] = useState( [ { lfrom : "" ,  lcontent : "" } ])

    function getletter()  {
        axios
            .get("/letter/toletter" , { params : {"page" : page } } )
            .then( re => {
                if( re.data.length == 0 ){
                    alert("쪽지가 없습니다");
                }else{
                    console.log( '쪽지리스트 : '+re.data );
                    setLetterList(re.data);
                }
            })
            .catch(err => {console.log('리스트 오류'+err);})
    }

    useEffect(getletter , [page] );

    const onPage = (page) => {
        setPage(page)
    }


    return(
        <div className="letterlistbox">
            <h3 className="letterh3"> 보낸 쪽지 함 </h3>
            <div className="homeline" > </div>
            <table className="tablelist">
                {
                    LetterList.map( (l, i ) => {
                        return(
                            <tr className="listreturn">
                                <td variant="primary" onClick={ () => handleShow(i) } >{ l.lfrom } </td>
                                <td>{l.lcontent}</td>
                            </tr>
                        )
                    })
                }
            </table>
            {
                <div className="Pagination" >
                <Pagination
                    activePage={ page  }
                    itemsCountPerPage = { 5 }
                    totalItemsCount = { LetterList == false ? (LetterList.totalletter) : 10 }
                    pageRangeDisplayed = { 5 }
                    onChange={ onPage }
                />
                </div>
             }

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
                                Value={ LetterList == false ? (LetterList[selectItem].lfrom) : null }
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
                            <Form.Control rows={6} className="lcontent" Value={LetterList[selectItem].lcontent} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}> 닫기 </Button>
                </Modal.Footer>
            </Modal>    {/*모달 - 쪽지보내기 end */}
    </div>
    )
}