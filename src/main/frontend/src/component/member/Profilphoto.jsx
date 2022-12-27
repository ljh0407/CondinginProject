import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

export default function Profilphoto(props){



    const [ login , setLogin ] = useState({}); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링

    axios
        .get("/member/getloginMno") //url 호출
        .then( (response) => { setLogin( response.data ); } )


    const mupdate = () => {window.location.href="/member/mupdate"}  //버튼을 눌렀을때 회원수정하기 페이지 이동

    return(
        <div>   {/*로그인된 회원 정보 출력*/}
            Email : <div name="memail">{login.memail}</div>
            닉네임 : <div name="mnick">{login.mnick}</div>
            프로필사진 : <img className="mprofileImg" src={"/static/media/"+ login.mfilename } />
            <button type="button" onClick={ mupdate }>수정하기</button>
        </div>
    )
}