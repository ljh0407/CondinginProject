import React , { useState , useEffect } from 'react';
import axios from "axios";
import {useParams} from "react-router-dom";
//테스트?
export default function Bview(props){
    /*12.16 게시물 상세보기*/
    const params = useParams();  // useParams() 훅 : 경로[URL]상의 매개변수 가져올때
        //게시물 , 리랜더링될 게시물           // 게시물 메모리
    const [ board , setBoard ] = useState({ });

    useEffect( // 1. 서버로 부터 해당 게시물번호의 게시물정보 요청
        () => axios
            //url주소 파라미터로 불러올 bno
            .get("/board/getbview" , { params : {bno : params.bno}})
            //setBoard에 데이터 담기
            .then( res => {setBoard(res.data)}) ,[]);

    //2. 게시물 삭제하기
    const getUpdate = () =>{

    }


    //3. 게시물 수정하기
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

                <button type="button" onClick={getUpdate}>수정</button>
                <button type="button" onClick={onDelete}>삭제</button>



        </div>

    )
}


/*

<div className="wrap">
    <h2>개별글 보기 페이지 입니다.</h2>

    <div className="boatdWrap">

        <div className="memberSection">
            <div className="mprofile"> 프로필</div>프로필
            <div>
                <span className="mnick">닉네임</span> 닉네임
                <span className="data">올린시간</span>

            </div>
        </div>
        <div className="boardsection">
            <div className="btitle">제목</div>
            <div className="bcontent">내용</div>
        </div>

        <div className="updown">
            <button type="button" className="likeno" onClick="likeno()">좋아요</button>
            <button type="button" className="dno" onClick="dno()">싫어요</button>
        </div>

        <div className="replySection"> 댓글작성을 하기 위해서는 로그인을 해야합니다

            <div className="mprofile">로그인한사람의 프로필사진</div>
            <textarea className="rcomment"> </textarea>
        </div> //replySection
    </div>
</div>
*/






