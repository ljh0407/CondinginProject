import React , { useState , useEffect } from 'react'
import axios from 'axios'
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { useParams  } from "react-router-dom";

let bcontent = ''   //내용 전역변수
export default function Bupdate( props ){   //글수정

    const params = useParams(); //경로[URL]상의 매개변수사용

    const [ board , setBoard ] = useState( [] );

    useEffect(      //통신.get방식      url호출               변수받기
        () => axios.get( "/board/getbview" , { params : { bno: params.bno } } )
            .then( res => { setBoard( res.data ) } )
            .catch(err => {console.log('수정오류 : '+err)})
        , [] )

    const upboard = () => {  // 1. 서버로부터 수정된 정보를 이용한 게시물 수정 요청
        // 수정할 게시물번호 , 수정할내용들[제목,내용,첨부파일]
        let boardform = document.querySelector('.boardform');
        let formdata = new FormData( boardform );   // 수정할 제목 , 첨부파일
        formdata.set( "bno" , board.bno ); // + 수정할 게시물번호 [ 수정할 식별자 필수!! ]
        formdata.set( "bcontent" , bcontent ); // + 수정할 게시물내용

        // 통신.put방식     url호출       폼 전송
        axios.put("/board/upboard" , formdata , {headers: {'Content-Type': 'multipart/form-data'}})
            .then( res => {
                if( res.data == true ){ alert('게시물 수정 성공'); window.location.href="/"}
                else{ alert('게시물 수정 실패'); }
            }).catch( err => { console.log( err ); } )

    }

    return (
        <div className="writing">

            <form className="boardform" value="put">
                <h1> 수정 페이지 </h1>
                <div className="bar"></div>
                {/*텍스트타입    디티오호출                    변경할 내용 호출(기본값)*/}
                <input type="text" name="btitle" className="btitle" id="btitle" placeholder ="제목을 입력해 주세요."  defaultValue={ board.btitle } />
                <CKEditor
                    editor={ ClassicEditor }
                    data= { board.bcontent }
                    onChange={ ( event, editor ) => { const data = editor.getData(); bcontent = data  } }
                />
                <button type="button" className="enrollment" onClick={ upboard } > 수정 </button>
            </form>
        </div>
    );
}