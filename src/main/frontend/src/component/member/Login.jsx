import React from 'react'

export default function Login (props){
    return(
        <div>
            <h3>로그인</h3>
              <form method="post" action="/member/getmember">
                  <a href="/oauth2/authorization/kakao"> 카카오 로그인 </a>
                  <a href="/oauth2/authorization/naver"> 네이버 로그인 </a>
                  <a href="/oauth2/authorization/google"> 구글 로그인 </a>
              </form>
        </div>
    )
}