import React,{useState, useEffect} from 'react';
import StyleSheet from '../css/home.css';
import {Link} from "react-router-dom";
import axios from "axios";// 12.16 최예은 추가
import HomeBoard from "./board/HomeBoard" // 12.19 최예은 추가
import git from '../img/git.png';
import Notion from '../img/Notion.png';
import conding from '../img/conding.png';
import Intellij from '../img/Intellij.png';
import react from '../img/react.png';
import springboot from '../img/springboot.png';

export default function Home( props ){
    // 카테고리 추가
    const [categorylist,setCategorylist] = useState([]);

    function clist(){    // 카테고리 가져오기
        axios
            .get("/board/getcategory")
            .then( res => {setCategorylist(res.data)})
            .catch(err=>{ console.log(err);})
    }
    useEffect( clist, [] );

    return(
        <>
            <div className="LayOut">   {/* 전체 div */}
                <div className="homeline" > </div>
                <div className="mid">

                    {/*출력한 각각의  cno , cname 뿌리기*/}
                    <div className="rightSection">
                        <div className="section01">
                            <div className="topSection section">
                                <HomeBoard cno={ 1 } bcname={ "공지사항" } />
                                <HomeBoard cno={ 2 } bcname={ "질문있어요" } />
                            </div>
                            <div className="bottomSection section">
                                <HomeBoard cno={ 3 } bcname={ "고민있어요" } />
                                <HomeBoard cno={ 4 } bcname={ "인기게시판" } />
                            </div>
                        </div>
                        <div className="section02">
                            <div className="eventSection"></div>
                        </div>
                    </div>

                    <article  className="sidebar">
                        {
                            categorylist.map((c)=>{
                                return(
                                    <div className="clist"> {/*{c.cname}*/} </div>
                                )
                            })
                        }
                        <div id="promotion">   {/* git , notion 이미지 */}
                            <a href="https://github.com/ljh0407/CondinginProject" target='_blank' rel='noreferrer'> <img className="hongbo" src={git} /> </a>
                            <a href="https://typhoon-swordtail-68e.notion.site/0e74dc862bbd45799d64654034bcfdc7" target='_blank' rel='noreferrer'> <img className="hongbo" src={Notion} /> </a>
                        </div>
                         <div className="banner">
                             <img className="conding" src={conding} />
                             <a href="https://www.jetbrains.com/ko-kr/idea/" target='_blank' rel='noreferrer'> <img className="Intellij" src={Intellij} /> </a>
                         </div>
                         <div className="banners">
                              <a href="https://ko.reactjs.org/" target='_blank' rel='noreferrer'> <img className="react" src={react} /> </a>
                              <a href="https://spring.io/projects/spring-boot" target='_blank' rel='noreferrer'> <img className="springboot" src={springboot} /> </a>
                         </div>
                    </article>

                </div>  {/* mid div */}
        </div>   {/* 전체 div */}
        </>
    )
}

/*
cno를 구별해야한다.


*/




