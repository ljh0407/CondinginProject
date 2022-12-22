import React , { useState , useEffect } from 'react';
import {useParams} from "react-router-dom";
import axios from "axios";
import Pagination from 'react-js-pagination'
import Bview from "./Bview"; // npm i react-js-pagination 설치 12.14 최예은 설치함

import StyleSheet from '../../css/Board/boardList.css'; // css 불러오기


export default function BoardList(props) {  //글목록

    const params = useParams();  //경로[URL]상의 매개변수 가져올때

    // 서버로부터 pageInfo 요청 [실행조건 1. 랜더링이 될때 2. 검색할 때 3.카테고리선택할때 4.페이징할 때 --> 일반함수화] //
    //      검색처리요소 , 리랜더링                             // 카테고리          페이징     검색  검색어
    const [pageInfo , setPageInfo] = useState({ cno : params.cno , page:1, key:"",keyword:""})
    //      페이지 , 리랜더링                          게시물리스트
    const [pageDto , setPageDto] = useState({list:[]})

    //--------------------1. 게시물출력-------------------------//
    function getboardlist(){  // pageinfo 요청 -> pageDto
        axios   //url                       검색처리요소
            .post("/board/getboardlist",pageInfo)
            .then(res => {
                console.log(res.pageInfo);
                console.log(res.data);
                setPageDto(res.data); })
            .catch(err => console.error(err))
    }
    useEffect(getboardlist,[pageInfo])




    // 페이징처리
    const onPage = (page) =>{
        setPageInfo(
            { cno : pageInfo.cno ,  // 카테고리
                page:  page ,     // 페이지
                key : pageInfo.key, // 검색
                keyword : pageInfo.keyword } // 키워드
        )}

    const onSerch = () =>{  // 검색 기능
        setPageInfo(
            { cno : pageInfo.cno ,
                page : 1 , // 검색시 첫페이지부터 보여주기
                key: document.querySelector('.key').value,
                keyword: document.querySelector('.keyword').value
            }
        )}
    {/* 게시물번호 넘기기(상세보기) */}
    const loadView=(bno)=>{ window.location = "/board/bview/" +bno }

    return(

            <div>
                <a href={"/board/bwrite/"+params.cno } >글쓰기</a>
                <table className="blist">
                {
                    pageDto.list.map( (b) => {
                        return(
                            <tr>
                            <td>{b.bno}</td>
                                {/*제목누르면 상세보기 */}
                            <td onClick={() => loadView(b.bno)}>{b.btitle}</td>
                            <td>{b.memail}</td>
                            <td>{b.bdate}</td>
                            <td>{b.bview}</td>
                            </tr>
                        )
                    })
                    }
                </table>

                <Pagination
                    activePage={ pageInfo.page  }
                    itemsCountPerPage = { 5 }
                    totalItemsCount = { pageDto.totalBoards }
                    pageRangeDisplayed = { 5 }
                    onChange={ onPage }
                />

                <div className="searchbox">
                    <select className="key">
                        <option value="btitle">제목</option>
                        <option value="bcontent">내용</option>
                    </select>
                    <input type="text" className="keyword" />
                    <button type="button" onClick={onSerch}> 검색 </button>
                </div>
            </div>
    );//return end
}