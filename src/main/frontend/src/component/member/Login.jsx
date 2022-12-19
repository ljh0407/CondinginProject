import React from 'react'
import StyleSheet from '../../css/login.css'; // css 불러오기

export default function Login (props){
    return(
        <div className="login">
            <h3 className="title">로그인</h3>
              <form method="post" action="/member/getmember">
                <div id="Log">
                  <a href="/oauth2/authorization/kakao">  카카오 로그인 </a>
                  <a href="/oauth2/authorization/naver"> 네이버 로그인 </a>
                  <a href="/oauth2/authorization/google"> 구글 로그인 </a>
                </div>
              </form>
        </div>
    )
}
