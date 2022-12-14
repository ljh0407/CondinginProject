import React from 'react';
import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import logo from '../img/logo.png'
//로그인영역
import Login from './member/Login';
//board 영역
import Bwrite from "./board/Bwrite";//글쓰기
import Bview from "./board/Bview";//글쓰기
//letter영역
import Lwrite from "./letter/Lwrite";
//css영역
import StyleSheet from '../css/Index.css'; // css 불러오기
import { HashRouter, BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';

export default function Index( props ){
    const menus = [
    { name : "공지사항" , path: "/" },
    { name : "고민" , path: "/" },
    { name : "Q&A" , path: "/" },
    { name : "이벤트" , path: "/" },
    { name : "인기게시판" , path: "/" }
    ];
     return  (
            <div className="box">
                <BrowserRouter>
                    <Header/>
                        <h3>메인페이지</h3>
                    <Footer/>
                    <Routes>
                        <Route path="/"  element={ < Home /> } />
                        <Route path="/member/signup"  element={ < Login /> } />
                        <Route path="/board/bwrite" element={ <Bwrite />} /> // 글쓰기
                        <Route path="/board/bview" element={ <Bview />} /> // 전체글보기
                        <Route path="/letter/lwrite" element={ <Lwrite />} />
                    </Routes>

                    <Footer/>
                </BrowserRouter>
            </div>
        );
    }
















