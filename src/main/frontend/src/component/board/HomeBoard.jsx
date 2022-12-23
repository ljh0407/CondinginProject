/* 1.임포트 */
import React,{ useState,useEffect } from 'react';
import axios from 'axios'; // 12.19 추가

//카테고리별로 가져올 게시물

/*-------*/
/* 2.전역변수 */
/*--------- */

// 3.컴포넌트[함수] 만들기
export default function HomeBoard( props ){

    const [getdesclist,setGetdesclist] = useState([

        { rno : 0 ,  btitle : "" , memail : ""}

    ]); //최신글 가져오기 12.23 최예은
    useEffect( ()=>{
            axios
                .get("/board/getdesclist" , { params : { cno : props.cno }} )
                .then(res=>{setGetdesclist(res.data); console.log(res.data)})
                .catch(err=>{console.log(err);})
    } ,[]  );

    /* ------------ 4.js , react[ 함수 , 지역변수]  -------------------*/

    // 1. axios cno 컨트롤 넘기고
    // 2. 컨트롤에서 서비스로 옮기고
    // 3. 서비스에서 최신 4개

    /* ------------------------------------------ */

    /* ------------ 5. html or jsx표현식 { }------------------*/
    return(
        <div className="blist">
           <h3> <a href={"/board/"+props.cno} > { props.bcname} </a> </h3>
           <table>
            <tr>
                <td> 번호 </td>
                <td> 제목 </td>
                <td> 작성자 </td>
            </tr>
            {
                getdesclist.map( (b) => {
                    return (
                                <tr>
                                    <td> { b.bno} </td>
                                    <td> { b.btitle} </td>
                                    <td> { b.memail} </td>
                                </tr>
                    );
                })
            }

           </table>
        </div>
    )
    /* -------------------------------------------*/
}