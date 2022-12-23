import React from "react";
import axios from "axios";
import StyleSheet from '../../css/Letter/lwrite.css'; // css 불러오기


export default function Lwrite(){

    const setletter = () =>{

        let info = {
            ltitle : document.querySelector('.ltitle').value ,
            lcontent : document.querySelector('.lcontent').value
        }
        console.log(info);
        axios
            .post("/letter/setletter" , info )
            .then( res => {
                console.log(res.info)
                if( res.info == true ){alert('편지보내기 성공');}
                else{ alert('게시물 작성 실패');}
            })
            .catch( err => { console.log( err ); } )
    }

    // 12-21 이태준 3:30 분 경 하다가 중단
    return(

        <div className="writing">
        <h1 className="tit">쪽지보내기</h1>
        <div className="bar"></div>
            <input type="text" className="ltitle" placeholder="제목을 입력해 주세요." /><br/>
            <textarea className="lcontent" placeholder="500자 이하로 작성해주세요"></textarea><br/>
              <input type="file" className="lfile" />
              <br/>
              <button type="button" onClick={setletter} className="lbutton">쪽지 보내기</button>
        </div>
    )


}

