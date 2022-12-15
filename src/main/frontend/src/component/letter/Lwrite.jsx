import React from "react";
import axios from "axios";

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

    return(

        <div>
        <h1>쪽지보내기</h1>
        제목 : <input type="text" className="ltitle" /><br/>
        내용 : <textarea className="lcontent" placeholder="500자이하로 작성해주세요"></textarea><br/>
              <input type="file" className="lfile" />
              <button type="button" onClick={setletter}>쪽지 보내기</button>
        </div>
    )


}