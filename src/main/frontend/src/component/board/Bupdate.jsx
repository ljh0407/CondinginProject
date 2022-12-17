import React , { useState , useEffect } from 'react'
import axios from 'axios'
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { useParams  } from "react-router-dom";

let bcontent = ''
export default function Bupdate( props ){
    const params = useParams();

    const [ board , setBoard ] = useState( [] );
    const [ login , setLogin ] = useState( [] );

    useEffect(
        ()=>axios.get( "/board/getbview" , { params : { bno: params.bno } } ) .then( res => { setBoard( res.data ) } )
        , [] )
    console.log(board+'고으닛');

    const upboard = () => {  // 1. 서버로부터 수정된 정보를 이용한 게시물 수정 요청
        alert('버튼')
        // 수정할 게시물번호 , 수정할내용들[제목,내용,첨부파일]
        let boardform = document.querySelector('.boardform');
        let formdata = new FormData( boardform );   // 수정할 제목 , 첨부파일
        formdata.set( "bno" , board.bno ); // + 수정할 게시물번호 [ 수정할 식별자 필수!! ]
        formdata.set( "bcontent" , bcontent ); // + 수정할 게시물내용
        console.log(board.bno);
        console.log(formdata);
        console.log(bcontent);

        axios.put("/board/bupboard" , formdata )
            .then( res => {
                if( res.data == true ){ alert('게시물 수정 성공'); }
                else{ alert('게시물 수정 실패'); }
            })
            .catch( err => { console.log( err ); } )
    }

    return (
        <div>
            <h1> 수정 페이지 </h1>
            <form className="boardform" value="put">
                제목 : <input type="text" name="btitle" defaultValue={ board.btitle } />
                <CKEditor
                    editor={ ClassicEditor }
                    data= { board.bcontent }
                    onChange={ ( event, editor ) => { const data = editor.getData(); bcontent = data  } }
                />
                <button type="button" onClick={ upboard } > 수정 </button>
            </form>
        </div>
    );
}