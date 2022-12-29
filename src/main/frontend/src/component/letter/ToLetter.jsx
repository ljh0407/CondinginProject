import React , { useState , useEffect } from "react";
import axios from "axios";

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import Pagination from "react-js-pagination";
export default function ToLetter(porps){    //받은 쪽지

    //---------------------------[쪽지보내기]----------------------------------//
    //리스트에서 lno찾기
    const [ selectItem2 , setSelectItem2 ] = useState(0);
    //선택한 모달을 키기위해 기본값 false
    const [show, setShow] = useState(false);    // 닫기
    //첫 페이지 1부터 시작
    const [page, setPage] = useState(1 );    // 페이지
    // 모달닫기
    const handleClose = () => setShow(false);   // 쪽지닫기
    // 선택한 쪽지 보기
    const handleShow = (i) => {
        setSelectItem2(i)   //랜더링 쪽지
        setShow(true);
    }     // 쪽지 열기

    //---------------------------[쪽지보내기]----------------------------------//
    // 쪽지 정보                                                   랜더링된 후 값을 넣기 위해 값 입력
    const [ LetterList2 , setLetterList2 ] = useState( [ { lto : "" ,  lcontent : "" } ])

    function getletter2()  {
        axios
            .get("/letter/toletter" , { params : {"page" : page } })
            .then( re => {
                if( re.data.length == 0 ){  //쪽지가 없을때
                    alert("쪽지가 없습니다");
                }else{  //쪽지 있을때
                    console.log( '쪽지리스트 : '+re.data );
                    setLetterList2(re.data);
                }
            })
            .catch(err => {console.log('리스트 오류'+err);})
    }

    useEffect(getletter2 , [page] );    //쪽지 정보와 페이징

    const onPage = (page) => { setPage(page) }  //페이징

    // 받은 쪽지 리스트
    return(
        <div className="letterlistbox">

        <Modal show={show} onHide={handleClose}>    {/* 닫기 */}
                <Modal.Header closeButton>
                    <Modal.Title> 쪽지 보기 </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>보낸 사람</Form.Label>
                            <Form.Control
                                type="email"
                                Value={ LetterList2 == false ? (LetterList2[selectItem2].lfrom) : null }
                                className="lto"
                                autoFocus
                                disabled    /*아이디 고정(내용 못고침)*/
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlTextarea1"
                        >
                            <Form.Label>내용</Form.Label> {/*쪽지 내용*/}
                            <Form.Control rows={6} className="lcontent"  Value={ LetterList2[selectItem2].lcontent } disabled
                            />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={ handleClose }> 닫기 </Button>
                </Modal.Footer>
            </Modal>    {/*모달 - 쪽지보내기 end */}

            <h3 className="letterh3"> 보낸 쪽지 함 </h3>
            <div className="homeline" > </div>
            <table className="tablelist">
                {
                    LetterList2.map( ( l , i ) => {
                        return(
                            <tr>
                                <td variant="primary" onClick={ () => handleShow(i) } >{ l.lto }</td>
                                <td>{ l.lcontent }</td> {/* 쪽지 내용 */}
                            </tr>
                        )
                    })
                }
            </table>
            <div className="Pagination" >
            <Pagination
                activePage={ page  }    //페이징
                itemsCountPerPage = { 5 }   //출력할 쪽지 수
                totalItemsCount = { LetterList2 == false ? (LetterList2.totalletter) : 5 } /* 버튼 수 */
                pageRangeDisplayed = { 5 }  //표시할 버튼 수
                onChange={ onPage }
            />
            </div>
        </div>
    )
}