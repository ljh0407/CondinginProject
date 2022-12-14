import React from "react";
import axios from "axios";
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

let bcontent = ''; // 12.14 고은시 입력받은 게시물 내용 [ 전역변수 ]  // 변수가 수정될경우 재랜더링할 필요 X

export default function Bwrite(props) {

    /*/////////////////////////////글쓰기/////////////////////////////*/
    const bwrite = () => {
        // 12.14 고은시 데이터 전송방식 변경
        let boardform = document.querySelector('.boardform');
        let formdata = new FormData(boardform);

        axios.post("/board/bwrite", formdata, {headers: {'Content-Type': 'multipart/form-data'}})
            .then(res => {
                console.log(res.data)
                if (res.data == true) {
                    alert('게시물 등록 성공');
                } else {
                    alert('게시물 등록 실패');
                }
            })
            .catch(err => {
                console.log(err + '글쓰기 오류')
            })
    }
    return (
        <div>
            <form className="boardform">
                <span>제목</span> <input type="text" name="btitle" className="btitle" id="btitle"/><br/>
                <CKEditor
                    editor={ ClassicEditor }
                    data=""
                    onChange={ ( event, editor ) => { const data = editor.getData(); bcontent = data  } }
                />
                <button type="button" onClick={bwrite}>작성하기</button>
            </form>
        </div>
    )

}