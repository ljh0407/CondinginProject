import React from "react";
import axios from "axios";
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

const bcontent = '';
export default function Bwrite(){
    //12.7 최예은 글쓰기 테스트
    alert("bwrite.js 확인")

    /*/////////////////////////////글쓰기/////////////////////////////*/
    const bwrite = () => {
        // 한꺼번에 전송하기 위해 객체로 만들어서 가져온다.
        let data = {
            btitle : document.querySelector(".btitle").value,
            bcontent: document.querySelector(".bcontent").value,
        }

        console.log("bwrite.js 확인" + data)
        console.log(data)

        axios
            .post("/board/bwrite")
            .then( res => {
                console.log( res.data )
                if( res.data == true ){ alert('게시물 작성 성공'); }
                else{ alert('게시물 작성 실패'); }
            })
            .catch( err => { console.log( err ); } )
    }

    return(
        <form className="boardform">
            <span>제목</span> <input type="text" name="btitle" className="btitle" id="btitle" /><br/>
                <CKEditor
                    editor={ ClassicEditor }
                    data=""
                    onChange={ ( event, editor ) => { const data = editor.getData(); bcontent = data  } }
                />
            <button type="button" onClick={bwrite}>작성하기</button>
        </form>
    )
}