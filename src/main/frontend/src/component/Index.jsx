import React from 'react';
import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import logo from '../img/logo.png'
//로그인영역
import Login from './member/Login';
import MUpdate from "./member/MUpdate";
import Profilphoto from "./member/Profilphoto";
//board 영역
import Bwrite from "./board/Bwrite";//글쓰기
import BoardList from "./board/BoardList"; //전체글보기12.15 최예은 추가
import Bview from "./board/Bview";//개별글보기
import Bupdate from "./board/Bupdate";  //게시글 수정
//letter영역
import Lwrite from "./letter/Lwrite";
import Letterlist from "./letter/Letterlist";
import ToLetter from "./letter/ToLetter";
import Lview from "./letter/Letterview";
//css영역
import StyleSheet from '../css/Index.css'; // css 불러오기
import { HashRouter, BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';
import Reply from "./reply/Reply";
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
                    <Routes>
                        <Route path="/"  element={ < Home /> } />
                        <Route path="/member/signup"  element={ < Login /> } /> {/*로그인*/}
                        <Route path="/board/bwrite/:cno" element={ <Bwrite />} /> {/*// 글쓰기*/}
                        <Route path="/board/bview/:bno" element={ <Bview />} /> {/*// 개별글보기*/}
                        <Route path="/letter/lwrite" element={ <Lwrite />} />   {/*//쪽지*/}
                        <Route path="/letter/letterlist" element={ <Letterlist />}  />   {/*//쪽지함*/}
                        <Route path="/letter/tolist" element={ <ToLetter />} />   {/*//받은쪽지함*/}
                        <Route path="/letter/letterview" element={ <Lview />} />   {/*//받은쪽지함*/}
                        <Route path="/board/update/:bno" element={ <Bupdate />} />   {/*//게시글 수정*/}
                        <Route path="/member/mupdate" element={ <MUpdate />} />   {/*//회원수정*/}
                        <Route path="/member/profile" element={ <Profilphoto />} />   {/*//프로필 출력*/}
                        <Route path="/board/:cno" element={ <BoardList />} /> {/* 글쓰기 */}
                        <Route path="/board/:cno" element={ <Reply />} /> {/* 댓글 */}
                    </Routes>
                    <Footer/>
                </BrowserRouter>
            </div>
        );
    }
//test