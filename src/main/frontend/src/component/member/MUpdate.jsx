import React , { useState , useEffect } from 'react';
import axios from "axios";
import css from '../../css/member/mupdate.css'


export default function MUpdate(props){

    //회원 , 리랜더링될 회원정보           // 회원 메모리
    const [ member , setMember ] = useState([ ]);

    //로그인 맞는지 확인
    const [ login , setLogin ] = useState(null); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
    const handleLogin= () => {
        axios.get("/member/getloginMno")    //url
            .then((res)=>{setLogin(res.data.split("_")[0]);})
            .catch(err => {console.log('출력오류'+err);})
    }
    useEffect(handleLogin , []);    //로그인은 여기서 체크하는거임

    const update = () => {
        let memberform = document.querySelector('.memberform'); // DOM 객체
        let formdata = new FormData(memberform);

        axios.post("/member/setmupdate" , formdata, {headers: {'Content-Type': 'multipart/form-data'}})
            .then(res => {
                if(res.data == true){alert('수정완료');}
                else {alert('수정실패')}
            })
            .catch(err => {console.log('회원수정에러 : '+err)})
    }
    return(
        <div className="bobobo">
            <h1>프로필 수정하기</h1>
            <form className="memberform" name="" value="post">
                프로필사진 <input type="file" name="mprofile"/>  {/*프로필사진*/}
                닉네임 <input type="text" className="mnick" name="mnick"/> {/*닉네임*/}
                <button type="button" onClick={ update }>수정하기</button>
            </form>
        </div>
    );
}