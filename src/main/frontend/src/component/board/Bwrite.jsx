import React from "react";
import axios from "axios";
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import StyleSheet from '../../css/Board/bwrite.css'; // css 불러오기


let bcno = 0; // 선택한 카테고리 번호 [ 전역변수 ]
let bcontent = ''; // 12.14 고은시 입력받은 게시물 내용 [ 전역변수 ]  // 변수가 수정될경우 재랜더링할 필요 X

export default function Bwrite(props) {

    const bwrite = () => {

        let boardform = document.querySelector('.boardform');   //form호출
        let formdata = new FormData(boardform); //FormData 저장
        formdata.set("bcontent",bcontent)   //FormData에 추가저장

        //통신.post버전 url호출           formdata전송      첨부파일있든없든 사용
        axios.post("/board/setboard", formdata, {headers: {'Content-Type': 'multipart/form-data'}})
            .then(res => {
                if (res.data == true) { alert('게시물 등록 성공'); }
                else { alert('게시물 등록 실패'); } })
            .catch(err => {
                console.log(err + '글쓰기 오류')
            })
    }
    return (
        <div className="writing">
            <form className="boardform">
                <h1 className="tit"> 카페 글 쓰기 </h1>
                <div className="bar"></div>
                <input type="text" name="btitle" className="btitle" id="btitle" placeholder ="제목을 입력해 주세요." /><br/>
                <CKEditor
                    editor={ ClassicEditor }
                    data=""
                    onChange={ ( event, editor ) => { const data = editor.getData(); bcontent = data  } }
                />
                <button type="button" className="enrollment" onClick={bwrite}>작성하기</button>    {/*함수실행*/}
            </form>
        </div>
    )
}