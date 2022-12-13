import React from 'react';
import Header from './Header';
//import promotion from './promotion';
import Home from './Home';
import Footer from './Footer';
import Bwrite from "./board/Bwrite";
import Bview from "./board/Bview";
import lwrite from "./letter/Lwrite";
import logo from '../img/logo.png'
import StyleSheet from '../css/Index.css'; // css 불러오기
import Login from './member/Login';

import { HashRouter, BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';
import Lwrite from "./letter/Lwrite";

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
                        <Route path="/board/bwrite" element={ <Bwrite />} />
                        <Route path="/board/bview" element={ <Bview />} />
                        <Route path="/letter/lwrite" element={ <Lwrite />} />
                    </Routes>

                    <Footer/>
                </BrowserRouter>
            </div>
        );
    }
















