import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

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
        <div>
                Email : <div name="memail">{member.memail}</div>
                닉네임 : <div name="mnick">{member.mnick}</div>
                프로필사진 : <div name="memprofilemail" typeof="file">{member.mprofile}</div>
                <button type="button" onClick={ mupdate }>수정하기</button>
        </div>
    )
}

