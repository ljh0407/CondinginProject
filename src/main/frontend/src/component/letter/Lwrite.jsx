import React from "react";
import axios from "axios";


export default function lwrite(){

    const setletter = () =>{

        // 편지쓰기 유효성 검사
        if( mno == 0 ){ alert('로그인부터 해주세요.'); return; }

        let ltitle = document.querySelector('.ltitle').value

    }

    return(

        <div>
        <h1>쪽지보내기</h1>
        제목 : <input type="text" className="ltitle" /><br/>
        내용 : <textarea className="lcontent" placeholder="500자이하로 작성해주세요"></textarea><br/>
              <input type="file" className="lfile" />
              <button type="button" onClick="setletter()">쪽지 보내기</button>
        </div>
    )


}