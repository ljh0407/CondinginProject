import React , { useState , useEffect } from 'react';
import axios from "axios";

let memberup = '';
export default function MUpdate(props){
    console.log('ㄱ공ㄴ시')

    //회원 , 리랜더링될 회원정보           // 회원 메모리
    const [ member , setMember ] = useState({ });

    //로그인 맞는지 확인
    const [ login , setLogin ] = useState({ }); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
    axios
        .get("/member/getloginMno") //url 호출          언더바 기준으로 자르기(작성자와 로그인한 사람확인)
        .then( (response) => { setLogin( response.data.split("_")[0] );  console.log( login ) } )

    const mupdate = () => {
        let memberform = document.querySelector('.memberform');
        let formdata = new FormData('.memberform' , ('#file')[0][1]);
        formdata.set("memail" , member.memail)    //수정할 회원
        console.log(formdata+'ㄱ공ㄴ시')

        axios.put("member/setmupdate" , formdata, {headers: {'Content-Type': 'multipart/form-data'}})
            .then(res => {
                if(res.data == true){alert('수정완료');}
                else {alert('수정실패')}
            })
            .catch(err => {console.log('회원수정에러 : '+err)})
    }
    return(
        <div>
            <h1>프로필 수정하기</h1>
            <form className="memberform" value="put">
                닉네임 <input type="text" className="mnick" defaultValue={ member.mnick } />
                프로필 <input type="file" className="mprofile" defaultValue={ member.mprofile } />
                <button type="button" onClick={ mupdate }>수정하기</button>
            </form>
        </div>
    );
}