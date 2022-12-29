import React , { useState , useEffect } from 'react';
import axios from "axios";
import css from '../../css/member/mupdate.css';

export default function MUpdate(props){ //회원수정

    //회원 , 리랜더링될 회원정보           // 회원 메모리
    const [ member , setMember ] = useState([ ]);

    //로그인 맞는지 확인
    const [ login , setLogin ] = useState(null); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
    const handleLogin= () => {
        axios.get("/member/getloginMno")    //url
            .then((res)=>{setLogin(res.data.split("_")[0]); console.log("값 확인 : "+res.data)})
            .catch(err => {console.log('출력오류'+err);})
    }
    useEffect(handleLogin , []);    //로그인 체크

    const update = () => {
        let memberform = document.querySelector('.memberform'); // DOM 객체
        let formdata = new FormData(memberform);

        axios.post("/member/setmupdate" , formdata, {headers: {'Content-Type': 'multipart/form-data'}})
            .then(res => {  //수정 성공하면 로그아웃하기
                if(res.data == true){alert('수정완료'); window.location.href="/member/logout" }
                else {alert('수정실패')}
            })
            .catch(err => {console.log('회원수정에러 : '+err)})
    }
    return(

        <div className="bobobo">
            <div className="homeline" > </div>
            <h1 className="uptitle">프로필 수정하기</h1>
            <form className="memberform" name="" value="post">
                <div>
                    <div class="button">
                        <label for="chooseFile">
                            👉 profile! 👈    {/*프로필 사진 수정*/}
                        </label>
                    </div>
                    <input type="file" name="mprofile" id="chooseFile" />  {/*프로필사진*/}
                </div>
                <input type="text" className="mnick" name="mnick" placeholder="변경할 닉네임을 입력해 주세요."/> {/*닉네임*/}

                <button type="button" onClick={ update } className="upbutton">수정하기</button>
            </form>
        </div>
    );
}