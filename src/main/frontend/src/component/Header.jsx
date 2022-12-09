import React from 'react';    // 컴포넌트 호출
import StyleSheet from '../css/header.css'; // css 불러오기
import logo from '../img/logo.png';         // 로고 이미지 적용
//import axios from '.axios'; // react 비동기 통신 라이브러리
import { BrowserRouter , Routes , Route , Link , Router} from 'react-router-dom';   // 라우타루 오늘 배운것 중에 제일 중요 *

export default function Header(props) {
    return(

        <header>
                <div className="box">
                    <div id="logo">
                        <Link to="/index"> <img className="logo" src={logo} /> </Link>
                    </div>
                    <nav id="menu">
                        <ul id="top_menu">
                            <li><Link to="/"> 쪽지</Link></li>
                            <li><Link to="/"> 내정보</Link></li>
                            <li ><Link to="/"> 회원가입 </Link> </li>
                        </ul>
                    </nav>
                </div>
            </header>

    );
}













