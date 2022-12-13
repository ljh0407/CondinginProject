import React from "react";
import axios from 'axios';

export default function Login(props){

    // 1. setmember 이벤트 함수 정의 [ 화살표함수 정의 ]
    const setmember = () => {
        let info = {    // 2. 입력받은 값 가져오기
            memail : document.querySelector('.memail').value ,
            mpassword : document.querySelector('.mpassword').value ,
            mphone : document.querySelector('.mphone').value
        }

        axios           // 3. axios 비동기통신 이용한 서버[spring] 통신
            .post( "/member/setmember" ,  info )   // 요청메소드 ( rul , data )
            .then( res => { // 응답
                let result = res.data ;
                if( result != 0 ){ // 회원가입 성공
                    alert('회원가입 성공')
                }else{ // 회원가입 실패
                    alert('회원가입 실패')
                }
            } )
            .catch( err => { console.log( err ) } )   // 예외처리
    }

    return(
        <div>
            <h3>로그인</h3>
            <form method="post" action="/member/getmember">
                이메일 : <input type="text" name="memail" /><br/>
                비밀번호 : <input type="password" name="mpassword" /><br/>
                <input type="submit" value="로그인" />
                <a href="/oauth2/authorization/kakao"> 카카오 로그인 </a>
                <a href="/oauth2/authorization/naver"> 네이버 로그인 </a>
                <a href="/oauth2/authorization/google"> 구글 로그인 </a>
            </form>
        </div>
    )
}