// import React , { useState , useEffect } from 'react';
// import axios from "axios";
// import 'bootstrap/dist/css/bootstrap.css';
//
// import Button from 'react-bootstrap/Button';
// import Form from 'react-bootstrap/Form';
// import Modal from 'react-bootstrap/Modal';
//
// export default function Lview(props){
//
//     const [ Lfrom , setLfrom ] = useState([]); // 보낸사람정보
//
//
//
//     useEffect(
//         () => axios
//             .get("/letter/lview")
//             .then( re =>{
//                 setLfrom(re.data); console.log("쪽지상세보기 : "+ re.data)
//             })
//             .catch(err => {console.log(err)})
//    ,[] )
//
//     // 모달
//     const [show, setShow] = useState(false);    // 닫기
//     const handleClose = () => setShow(true);     // 닫기 버튼
//     //------------------------------------------------------------//
//
//
//     return(
//
//         <div>
//             <Modal show={show} onHide={handleClose}>
//                 <Modal.Header closeButton>
//                     <Modal.Title>받은 쪽지함</Modal.Title>
//                 </Modal.Header>
//                 <Modal.Body>
//                     <Form>
//                         <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
//                             <Form.Label>보낸사람</Form.Label>
//                             <Form.Control
//                                 type="email"
//                                 placeholder="name@example.com"
//                                 Value={Lfrom.lfrom}
//                                 className="lfrom"
//                                 autoFocus
//                                 disabled
//                             />
//                         </Form.Group>
//                         <Form.Group
//                             className="mb-3"
//                             controlId="exampleForm.ControlTextarea1"
//                         >
//                             <Form.Label>쪽지내용</Form.Label>
//                             <Form.Control as="textarea"
//                                           rows={6}
//                                           className="lcontent"
//                                           value={Lfrom.lcontent}
//                                           disabled
//                             />
//                         </Form.Group>
//                     </Form>
//                 </Modal.Body>
//                 <Modal.Footer>
//                     <Button variant="secondary" onClick={handleClose}> 닫기 </Button>
//                 </Modal.Footer>
//             </Modal>    {/*모달 - 쪽지보내기 end */}
//
//
//
//         </div>
//     )
//
// }
