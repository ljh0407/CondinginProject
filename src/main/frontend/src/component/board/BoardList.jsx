import React , { useState , useEffect } from 'react';
import axios from "axios";
import Pagination from 'react-js-pagination' // npm i react-js-pagination 설치 12.14 최예은 설치함

export default function BoardList(props) {
//1. 메모리
    const [pageInfo , setPageInfo] = useState({page:1, key:"",keyword:""})//1.요청정보객체state
    const [pageDto , setPageDto] = useState({list:[]}) // 게시물리스트 state
    //서버로부터 pageInfo 요청 [실행조건? 1. 랜더링이 될때 2. 검색할 때 3.카테고리선택할때 4.페이징할 때 --> 일반함수화]
//--------------------1. 게시물출력-------------------------

    function getboardlist(){
        axios
            .post("/board/getboardlist",pageInfo)
            .then(res => {
                console.log(res.data);
                setPageDto(res.data); })
            .catch(err => console.error(err))
    }
    useEffect(getboardlist,[pageInfo])


    return(
    <div>
        {

            pageDto.list.map((b) => {
                <div>
                    <div className='blist'>
                        <div className='profile'>
                            <div className='mprofile'>{ b.mprofile }</div> {/*프로필사진*/}
                        </div>
                        <div  className='boardSection'>
                            <div className='writeNview'>
                                <span className='bwrite'>{ b.mno }</span>{/*작성자*/}
                                <span className='bdate'>{ b.bdate }</span>{/*작성시간*/}
                            </div>
                            <div className='title'>
                                <a className='btitle' onClick={ (b.bno) } > {b.btitle} </a> {/*글제목*/}
                            </div>
                        </div>
                        <div>
                            <span className='bview'>{ b.bview }</span>{/*조회수*/}
                            <span className='bgood'>{ b.bgood }</span>{/*좋아요수*/}
                        </div>
                    </div>
                </div>
            })
        }

    </div>
    );//return end

}

/*        <Pagination
            activePage={ pageInfo.page  }
            itemsCountPerPage = { 5 }
            totalItemsCount = { pageDto.totalBoards }
            pageRangeDisplayed = { 5 }
            onChange={ onPage }
        />*/