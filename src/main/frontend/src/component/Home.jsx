import React,{useState, useEffect} from 'react';
import StyleSheet from '../css/home.css';
import {Link} from "react-router-dom";
import axios from "axios";// 12.16 최예은 추가

export default function Home( props ){


    //0. 12.16 최예은 추가
    const [categorylist,getCategorylist] = useState([]); // db에 등록해놓은 카테고리 리스트

    useEffect(
        ()=>axios
            .post("/board/getcategory")
            .then( res => getCategorylist (res.data))
            .catch(err=>{ console.log(err);})
    ,[])



    return(
        <>
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
        </div>   {/* 전체 div */}
        </>
    )
}










