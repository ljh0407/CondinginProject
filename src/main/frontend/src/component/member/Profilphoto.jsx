import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

import css from '../../css/member/profilphoto.css'

export default function Profilphoto(props){
    const [member ,setMember] = useState(  []);
    useEffect(
        ()=>axios.get("/member/upprofile")
            .then(res => {setMember(res.data); console.log("1번 : "+res.data)})
            .catch(err => {console.log('프로필 오류 : '+err)})
        ,[]
    )
    const [ login , setLogin ] = useState(null); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링

    axios
        .get("/member/getloginMno") //url 호출                    언더바 기준으로 자르기(작성자와 로그인한 사람확인)
        .then( (response) => { setLogin( response.data.split("_")[0] );  console.log("데이터 : "+ response.data ) } )
    console.log( "멤버" + member.memail)

    const mupdate = () => {window.location.href="/member/mupdate"}  //버튼을 눌렀을때 회원수정하기 페이지 이동

    return(
        <div className="pro">
                <div className="probox">
                    <div name="memprofilemail" typeof="file" className="profil">{member.mprofile}</div> {/* 프로필 사진 */}
                </div>
                <div className="proemailbox">
                    Email
                    <div name="memail" className="proemail">{member.memail}</div>
                </div>
                <div className="probickbox">
                    닉네임
                    <div name="mnick" className="pronick">{member.mnick}</div>
                </div>

                <div className="proboard"> </div>
                <button type="button" onClick={ mupdate } className="probut">수정하기</button>
        </div>
    )
}

