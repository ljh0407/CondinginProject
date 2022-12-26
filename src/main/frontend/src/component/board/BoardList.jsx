import React , { useState , useEffect } from 'react';
import {useParams} from "react-router-dom";
import axios from "axios";
import Pagination from 'react-js-pagination'
import Bview from "./Bview"; // npm i react-js-pagination 설치 12.14 최예은 설치함


import 'bootstrap/dist/css/bootstrap.css';


import StyleSheet from '../../css/Board/boardList.css'; // css 불러오기


export default function BoardList(props) {  //글목록

    const params = useParams();  //경로[URL]상의 매개변수 가져올때

    // 서버로부터 pageInfo 요청 [실행조건 1. 랜더링이 될때 2. 검색할 때 3.카테고리선택할때 4.페이징할 때 --> 일반함수화] //
    //      검색처리요소 , 리랜더링                             // 카테고리          페이징     검색  검색어
    const [pageInfo , setPageInfo] = useState({ cno : params.cno , page:1, key:"",keyword:""})
    //      페이지 , 리랜더링                          게시물리스트
    const [pageDto , setPageDto] = useState({list:[]})

    // 조회수 증가 12.23 최예은 추가함
    const [setview,setSetview] = useState([])

    //--------------------1. 게시물출력-------------------------//
    function getboardlist() {  // pageinfo 요청 -> pageDto
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
    const loadView=(bno)=>{

        ///////////////////조회수 증가하는 axios 12.23 최예은///////////////////
        axios
            .get("/board/setview",{params:{bno:bno}})
            .then(res=>{setSetview(res.data); console.log(res.data)})
            .catch(err=>console.error(err))
        ///////////////////////////////////////////////////////
        window.location = "/board/bview/" +bno
    }

    return(

            <div className="blistrout">
                <div className="awriting">
                <a href={"/board/bwrite/"+params.cno } className="atag" style={{color:'#000'}} >글쓰기</a>
                </div>
                <table className="clist">
                    <tr>
                        <td className="got1">순서</td>
                        <td className="got2">제목</td>
                        <td className="got3">이메일</td>
                        <td className="got4">날짜</td>
                        <td className="got5">조회수</td>
                    </tr>
                {
                    pageDto.list.map( (b) => {
                        return(
                            <tr className="table">
                            <td className="got6">{b.bno}</td>
                                {/*제목누르면 상세보기 */}
                            <td onClick={() => loadView(b.bno)} className="got2">{b.btitle}</td>
                            <td className="got7">{b.memail}</td>
                            <td className="got8">{b.bdate}</td>
                            <td className="got9">{b.bview}</td>
                            </tr>
                        )
                    })
                    }
                </table>

                <div className="searchbox">

                <Pagination
                    activePage={ pageInfo.page  }
                    itemsCountPerPage = { 5 }
                    totalItemsCount = { pageDto.totalBoards }
                    pageRangeDisplayed = { 5 }
                    onChange={ onPage }
                />

                    <select className="key">
                        <option value="btitle">제목</option>
                        <option value="bcontent">내용</option>
                    </select>
                    <input type="text" className="keyword" />
                    <button type="button" onClick={onSerch} className="blistbut"> 검색 </button>
                </div>
            </div>
    );//return end
}