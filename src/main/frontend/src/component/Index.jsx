import React from 'react';
import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import logo from '../img/logo.png'
//로그인영역
import Login from './member/Login';
import MUpdate from "./member/MUpdate";
//board 영역
import Bwrite from "./board/Bwrite";//글쓰기
import BoardList from "./board/BoardList"; //전체글보기12.15 최예은 추가
import Bview from "./board/Bview";//개별글보기
import Bupdate from "./board/Bupdate";  //게시글 수정
//letter영역
import Lwrite from "./letter/Lwrite";
//css영역
import StyleSheet from '../css/Index.css'; // css 불러오기
import { HashRouter, BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';
//
export default function Index( props ){

    const menus = [
    { name : "공지사항" , path: "/" },
    { name : "고민있어요" , path: "/" },
    { name : "Q&A" , path: "/" },
    { name : "이벤트" , path: "/" },
    { name : "인기게시판" , path: "/" }
    ];
     return  (
            <div className="box">
                <BrowserRouter>
                    <Header/>
                        <h3>메인페이지</h3>
                    <Routes>
                        <Route path="/"  element={ < Home /> } />
                        <Route path="/member/signup"  element={ < Login /> } />
                        <Route path="/board/bwrite" element={ <Bwrite />} /> {/*// 글쓰기*/}
                        <Route path="/board/boardlist" element={ <BoardList />} /> {/*// 글쓰기*/}
                        <Route path="/board/bview/:bno" element={ <Bview />} /> {/*// 개별글보기*/}
                        <Route path="/letter/lwrite" element={ <Lwrite />} />   {/*//쪽지*/}
                        <Route path="/board/update/:bno" element={ <Bupdate />} />   {/*//게시글 수정*/}
                        <Route path="/member/mupdate" element={ <MUpdate />} />   {/*//회원수정*/}
                    </Routes>
                    <Footer/>
                </BrowserRouter>
            </div>
        );
    }
//test