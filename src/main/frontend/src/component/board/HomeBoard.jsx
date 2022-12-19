/* 1.임포트 */
import React,{ useState,useEffect } from 'react';

import axios from 'axios'; // 12.19 추가

//카테고리별로 가져올 게시물
const [cno , setCno]  useState([]);
/*-------*/

/* 2.전역변수 */

/*--------- */

// 3.컴포넌트[함수] 만들기
export default function HomeBoard( props ){

    /* ------------ 4.js , react[ 함수 , 지역변수]  -------------------*/

    // 1. axios cno 컨트롤 넘기고
    // 2. 컨트롤에서 서비스로 옮기고
    // 3. 서비스에서 최신 4개

    /* ------------------------------------------ */
    axios
        .post("/board/getboardlist","cno" : cno)
        .then(res=>())
        .catch(err=>{console.log(err)})









    /* ------------ 5. html or jsx표현식 { }------------------*/
    return(
        <div className="blist blist01">
           <h3> { props.bcname} </h3>
           <table>
            <tr>
                <td> 게시물출력 1 </td>
            </tr>
           </table>
        </div>
    )
    /* -------------------------------------------*/
}