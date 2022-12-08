import React from 'react';
import Header from './Header';
import Footer from './Footer';
import logo from '../img/logo.png'
import StyleSheet from '../css/Index.css'; // css 불러오기
import { HashRouter, BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';

export default function Index( props ){
     return  (
            <div className="box">
                <BrowserRouter>
                    <Header/>
                        <div className="LayOut">    {/* 레이아웃 전체 */}
                            <div className="Photo">
                            </div>

                            <article  className="sidebar">
                                <li> 공지사항 </li>
                            </article>

                            <div className="post">
                            </div>

                        </div>                      {/* 레이아웃 전체 */}
                    <Footer/>
                    <Routes>
                        <Route path="/"  />
                    </Routes>
                </BrowserRouter>
            </div>
        );
    }
















