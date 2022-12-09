import React from 'react';
import Header from './Header';
//import promotion from './promotion';
import Footer from './Footer';
import logo from '../img/logo.png'
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
            <div className="box">   {/* 전체 box */}
                <BrowserRouter>
                    <Header/>
                            <div className="LayOut">   {/* 전체 div */}
                                 <div className="mid">
                                    <article  className="sidebar">
                                        <ul>
                                           <li> <Link to="/"> 공지사항 </Link> </li>
                                           <li> <Link to="/"> 고민 </Link> </li>
                                           <li> <Link to="/"> Q&A </Link> </li>
                                           <li> <Link to="/"> 이벤트 </Link> </li>
                                           <li> <Link to="/"> 인기게시판 </Link> </li>
                                        </ul>
                                    </article>

                                        <div className="rightSection">
                                            <div className="section01">
                                                <div className="topSection section">
                                                    <div className="blist blist01"></div>
                                                    <div className="blist blist02"></div>
                                                </div>
                                                <div className="bottomSection section">
                                                    <div className="blist blist03"></div>
                                                    <div className="blist blist04"></div>
                                                </div>
                                            </div>
                                            <div className="section02">
                                                <div className="eventSection"></div>
                                            </div>
                                        </div>

                                </div>  {/* mid div */}

                            <Routes>
                                <Route path="/" element={ <Header /> }  />
                            </Routes>
                            </div>   {/* 전체 div */}
                    <Footer/>
                </BrowserRouter>
            </div>
        );
}


















