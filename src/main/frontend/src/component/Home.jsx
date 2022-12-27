import React,{useState, useEffect} from 'react';
import StyleSheet from '../css/home.css';
import {Link} from "react-router-dom";
import axios from "axios";// 12.16 최예은 추가
import HomeBoard from "./board/HomeBoard" // 12.19 최예은 추가
import git from '../img/git.png';
import Notion from '../img/Notion.png';

export default function Home( props ){
    //0. 12.16 최예은 추가
    const [categorylist,setCategorylist] = useState([]); // 왜 사라짐?



    // 카테고리 가져오기
    /////////////////////////////db에서 insert한 카테고리 출력/////////////////////////////
    function clist(){
        axios
            .get("/board/getcategory")
            .then( res => {setCategorylist(res.data)})
            .catch(err=>{ console.log(err);})
    }
    useEffect( clist, [] );





    //console.log("카테고리리스트");
    //console.log(categorylist);



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
                                                <a href="https://github.com/ljh0407/CondinginProject"> <img className="hongbo" src={git} /> </a>
                                                <a href="https://typhoon-swordtail-68e.notion.site/0e74dc862bbd45799d64654034bcfdc7"> <img className="hongbo" src={Notion} /> </a>
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




