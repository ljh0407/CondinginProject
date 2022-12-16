import React , { useState , useEffect } from 'react';
import axios from "axios";
import {useParams} from "react-router-dom";

export default function Bview(props){
    /*12.16 게시물 상세보기*/
    const params = useParams();  // useParams() 훅 : 경로[URL]상의 매개변수 가져올때
    //게시물 , 리랜더링될 게시물           // 게시물 메모리
    const [ board , setBoard ] = useState({ });

    useEffect( // 1. 서버로 부터 해당 게시물번호의 게시물정보 요청
        () => axios
            //컨트롤 목록조회url                   bno받기
            .get("/board/getbview" , { params : {bno : params.bno}})
            //setBoard에 데이터 담기
            .then( res => {setBoard(res.data)}) ,[]);

    //로그인 맞는지 확인
    const [ login , setLogin ] = useState(null); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
    axios
        .get("/member/getloginMno") //url 호출                    언더바 기준으로 자르기(작성자와 로그인한 사람확인)
        .then( (response) => { setLogin( response.data.split("_")[0] );  console.log( login ) } )

    // 2. 해당 게시물번호의 해당하는 업데이트 페이지로 이동
    const getUpdate = () => { window.location.href='/board/update/'+params.bno;  }

    //3. 게시물 삭제하기
    const onDelete = () =>{
        axios
            .delete("/board/delboard",{params:{bno:params.bno}})
            .then(res => {alert("게시물삭제성공"); window.location.href="/board/boardlist"})
    }

    return(
        <div>
            <div>{board.btitle}</div>
            <div>{board.bcontent}</div>
            <div>{board.bdate}</div>
            <div>{board.bview}</div>
            <div>{board.bgood}</div>
            <div>{board.bbad}</div>
            <div>{board.memail}</div>
            <div>{board.mprofile}</div>

            {/*12.16 작성자와 로그인이 같으면 버튼 노출*/}
            { login==board.memail && <button type="button" onClick={onDelete}>삭제</button> }
            { login==board.memail && <button type="button" onClick={getUpdate}>수정</button> }
        </div>

    )
}