import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

import css from '../../css/member/profilphoto.css'

export default function Profilphoto(props){ //회원정보 출력

    const [ login , setLogin ] = useState({}); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링

    axios
        .get("/member/getloginMno") //url 호출
        .then( (response) => { setLogin( response.data ); } )


    const mupdate = () => {window.location.href="/member/mupdate"}  //버튼을 눌렀을때 회원수정하기 페이지 이동

    return(
        <div className="pro">   {/*로그인된 회원 정보 출력*/}
            <div className="homeline" > </div>
            <div className="probox">
                <img className="mprofileImg" src={"/static/media/"+ login.mfilename } />    {/* 프로필 사진 */}
            </div>
            <div className="proemailbox">
                Email
                <div name="memail" className="proid">{login.memail}</div>   {/*로그인한 회원의 이메일*/}
            </div>
            <div className="probickbox">
                닉네임
                <div name="mnick" className="prombick">{login.mnick}</div>  {/*로그인한 회원의 닉네임*/}
            </div>
            <div className="proboard"> </div>
            <button type="button" onClick={ mupdate } className="probut">수정하기</button>
        </div>
    )
}