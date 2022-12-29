import React , { useState , useEffect } from "react";
import {useParams} from "react-router-dom";
import axios from "axios";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Pagination from "react-js-pagination";

import StyleSheet from '../../css/Letter/Letterlist.css'; // css 불러오기

export default function Letterlist(porps){  //from쪽지 리스트

    //---------------------------[쪽지보내기]----------------------------------//
    //쪽지리스트의 인덱스 , 선택한 인덱스
    const [ selectItem , setSelectItem ] = useState(0);

    const [show, setShow] = useState(false);    // 닫기

    const [page, setPage] = useState(1 );    // 페이지
    const handleClose = () => setShow(false);   // 쪽지닫기

    const handleShow = (i) => {
        setSelectItem(i);   //선택한 쪽지
        setShow(true);  //열기
    }     // 쪽지 열기

    //---------------------------[쪽지보내기]----------------------------------//
    //쪽지정보          ,   랜더링 쪽지                              버퍼링걸리지않게 데이터 담아주기
    const [ LetterList , setLetterList ] = useState( [ { lfrom : "" ,  lcontent : "" } ])
    function getletter()  { //쪽지 정보 가져오기
        axios   //페이징은 경로로 인수받기
            .get("/letter/fromletter" , { params : {"page" : page } } )
            .then( re => {
                if( re.data.length == 0 ){  //쪽지가 없을 때
                    alert("쪽지가 없습니다");
                }else{  //쪽지 있을때
                    console.log( '쪽지리스트 : '+re.data );
                    setLetterList(re.data); //데이터 출력
                }
            })
            .catch(err => {console.log('리스트 오류'+err);})
    }

    useEffect(getletter , [page] ); //정보전달 , 페이징
    const onPage = (page) => { setPage(page) }  //???????????


    return(
        <div className="letterlistbox">
            <h3 className="letterh3"> 받은 쪽지 함 </h3>
            <div className="homeline" > </div>
            <table className="tablelist">
                {   //쪽지정보와 인덱스 넘기기
                    LetterList.map( (l, i ) => {
                        return(
                            <tr className="listreturn">
                                <td variant="primary" onClick={ () => handleShow(i) } >{ l.lfrom } </td>
                                <td>{l.lcontent}</td>   {/*쪽지 내용*/}
                            </tr>
                        )
                    })
                }
            </table>
            {
                <div className="Pagination" >
                <Pagination
                    activePage={ page  }    /*페이징*/
                    itemsCountPerPage = { 5 }   //출력할 전체 게시물 수
                    totalItemsCount = { LetterList == false ? (LetterList.totalletter) : 5 }   //버튼 수
                    pageRangeDisplayed = { 5 }  //표시할 전체 버튼 수
                    onChange={ onPage } //페이징 버튼 표시
                />
                </div>
             }

            <Modal show={show} onHide={handleClose}>    {/*모달 닫기*/}
                <Modal.Header closeButton>
                    <Modal.Title>쪽지 보기</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>받는 사람</Form.Label>
                            <Form.Control
                                type="email"    /*선택한 쪽지의 이메일 가져오기*/
                                Value={ LetterList == false ? (LetterList[selectItem].lto) : null }
                                className="lfrom"
                                autoFocus
                                disabled    //아이디 고정(내용 못고침)
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlTextarea1"
                        >
                            <Form.Label>내용</Form.Label> {/*선택한 쪽지의 내용*/}
                            <Form.Control rows={6} className="lcontent" Value={LetterList[selectItem].lcontent} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}> 닫기 </Button> {/*닫기*/}
                </Modal.Footer>
            </Modal>    {/*모달 - 쪽지보내기 end */}
    </div>
    )
}