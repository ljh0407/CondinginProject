import React from 'react'
import StyleSheet from '../../css/login.css'; // css 불러오기
import kakao from '../../img/kakao.png';
import naver from '../../img/naver.png';
import google from '../../img/google.png';

export default function Login (props){
    return(
        <div className="login">
            <h3 className="title">Login</h3>
              <form method="post" action="/member/getmember">
                <div id="Log">
                  <a href="/oauth2/authorization/kakao"> <img className="logimg" src={kakao} /> </a>
                  <a href="/oauth2/authorization/naver"> <img className="logimg" src={naver} /> </a>
                  <a href="/oauth2/authorization/google"> <img className="logimg" src={google} /> </a>
                </div>
              </form>
        </div>
    )
}
