// import React , { useState , useEffect } from 'react';
// import {useParams} from "react-router-dom";
// import axios from "axios";
// import {useParams} from "react-router-dom";
// import {CKEditor} from "@ckeditor/ckeditor5-react";
// import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
//
// let rcomment = '';  //댓글
// let bno = 0;   //게시물 번호
// export default function Reply(props){
//     const params = useParams();  //경로[URL]에서 bno가져오기
//
//     //      댓글 , 리랜더링 댓글
//     const [Reply , setReply] = useState( [] );
//
//     function setReply(){
//         axios.post("/reply/setreply")
//             .then(res => {setReply(res.data)})
//             .catch(err => {console.log('댓글오류'+err)})
//     }
//
//     useEffect(setReply , [])
//
//     const rwrite = () => {
//         let replyform = document.querySelector('.replyform');   //form호출
//         let formdata = new FormData(replyform); //FormData 저장
//         formdata.set("rcomment",rcomment)   //FormData에 게시글 내용추가
//         formdata.set("bno",params.bno)   //FormData에 cno 추가
//
//         //통신.post버전 url호출           formdata전송      첨부파일있든없든 사용
//         axios.post("/reply/setreply", formdata, {headers: {'Content-Type': 'multipart/form-data'}})
//             .then(res => {
//                 if (res.data == true) { alert('댓글 등록 성공');
//                     window.location.href="/board/"+params.bno;
//                 } else { alert('댓글 등록 실패'); } })
//             .catch(err => { console.log(err + '글쓰기 오류') })
//     }
//
//     return(
//         <div className="writing">
//             <form className="replyform">
//                 <div className="bar"></div>
//                 <input type="text" name="rcomment" className="rcomment" id="rcomment" placeholder ="내용을 입력해주세요." /><br/>
//                 <CKEditor
//                     editor={ ClassicEditor }
//                     data=""
//                     onChange={ ( event, editor ) => { const data = editor.getData(); rcomment = data  } }
//                 />
//                 <button type="button" className="enrollment" onClick={ rwrite }>작성하기</button>    {/*함수실행*/}
//             </form>
//         </div>
//     );
// }