import React , { useState } from 'react';    // 컴포넌트 호출
import StyleSheet from '../css/header.css'; // css 불러오기
import logo from '../img/logo.png';         // 로고 이미지 적용
import axios from 'axios'; // react 비동기 통신 라이브러리
import { BrowserRouter , Routes , Route , Link , Router} from 'react-router-dom';   // 라우타루 오늘 배운것 중에 제일 중요 *

export default function Header(props) {

    const [ login , setLogin ] = useState({}); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링

    axios
        .get("/member/getloginMno")
        .then( (response) => { setLogin( response.data ); } )

    //서버로부터 해당 게시물 번호의 게시물 번호 요청 value
    return(

        <header>
            <div className="box">
                <div id="logo">
                    <a href="/"> <img className="logo" src={logo} className="hdlogo" /> </a>   {/* 인덱스로 이동 */}
                </div>
                {login == "" ?
                    (   /*로그인 안했을떄*/
                        <nav id="menu">
                            <ul id="top_menu">
                                <li ><a href="/member/signup" className="atag" style={{color:'#000'}}> 회원가입 및 로그인 </a> </li>
                            </ul>
                        </nav>
                    )
                    :
                    (   /*로그인 했을때*/
                        <nav id="menu">
                            <ul id="top_menu">
                                <li> { login.mnick == null ? login.memail : login.mnick }  </li>    {/* 로그인 한 사람의 닉네임 없으면 이메일 표시 */}
                                {/* 로그인 한 사람의 프로필 사진 */}
                                <a href="/member/profile" className="amprofileImg"> <img className="mprofileImg" src={"/static/media/"+ login.mfilename } /> </a>
                                <li> <a href="/member/logout" className="atag" style={{color:'#000'}}> 로그아웃 |     </a> </li>
                                <li> <a href="/letter/Letterlist" className="atag" style={{color:'#000'}}>보낸 쪽지함 |</a> </li>
                                <li> <a href="/letter/tolist" className="atag" style={{color:'#000'}}>받은 쪽지함</a> </li>
                            </ul>
                        </nav>
                    )
                }
            </div>
        </header>
    );
}













