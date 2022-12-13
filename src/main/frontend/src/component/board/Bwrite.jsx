import React,{useState ,useEffect}  from "react";
import axios from "axios";
import { CKEditor } from '@ckeditor/ckeditor5-react';//12.13 최예은 추가
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';//12.13 최예은 추가

export default function Bwrite( props ){
    //12.7 최예은 글쓰기 테스트
    alert("bwrite.js 확인")

    /*/////////////////////////////글쓰기/////////////////////////////*/
    function bwrite(){

        // 한꺼번에 전송하기 위해 객체로 만들어서 가져온다.
/*        let data = {
            btitle : document.querySelector(".btitle").value,
            bcontent: document.querySelector(".bcontent").value,
        }*/

        let boardform = document.querySelector(".boardform");
        let formdata = new FormData(boardform)
        formdata.set( "bcontent" ,bcontent ); // 폼데이터의 내용추가ckeditor로 인해 추가 12.13

        axios
            .post("/board/bwrite",formdata)
            .then( res => {
                console.log( res.data )
                if( res.data == true ){ alert('게시물 작성 성공'); } // 하고 글 목록 페이지로 넘어가야함
                else{ alert('게시물 작성 실패'); }
            })
            .catch( err => { console.log( err ); } )
    }

    return(
        <form className="boardform">
            <span>제목</span> <input type="text" name="btitle" className="btitle" id="btitle" /><br/>
            <span>내용</span> <textarea id="summernote" name="bcontent" className="bcontent" style="resize: none;" /><br/>
            <button type="button" onClick="bwrite()">작성하기</button>
        </form>
    )
}