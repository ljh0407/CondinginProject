import React , { useState , useEffect } from "react";
import axios from "axios";
import css from "../../css/Reply/Rereply.css"


export default function Rereply(porps){

const deltererply=(reno)=>{
    //alert(reno)
    //console.log(reno)
    axios
        .delete("/reply/deleterereply",{ params : { "reno" : reno }})
        .then(res=>{
            if(res.data==true){alert("댓글삭제성공!")}
            else{alert("댓글삭제실패")}
        })
        .catch(err=>{console.log(err)})
}

    //로그인 맞는지 확인
    const [ login , setLogin ] = useState([]); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
        useEffect( // 1. 서버로 부터 해당 게시물번호의 시물정보 요청
        () =>  axios
          .get("/member/getloginMno") //url 호출
          .then( (response) => { setLogin( response.data );  console.log( login ) } ) ,[]
        );

    return(
        <div>{
            porps.data.map( (rr) => {
                return (
                <>
                    {/*css 작업은 Rereply.css에서 작업했습니다 참고부탁드립니다.*/}
                    <div className="rereplyWrap">

                        <div className="rereplyInformation">
                            <div className="rereplyProfileImg">{/*대댓글작성자의 프로필이미지 안들어옴*/}
                                <img src={"/static/modia/"+rr.bfilename}/>
                            </div>{/*rereplyProfileImg*/}
                            <div className="rereplyInfor">
                                <div className="rereplyMemail">작성자 : {rr.memail}</div>{/*작성자..왜 null값이 들어올까요?*/}
                                <div className="rereplyDate">작성시간 : {rr.bdate}</div>
                            </div>{/*rereplyInfor*/}
                        </div>{/*rereplyInformation*/}



                        <div className="rereplyList">{rr.rercomment}</div>{/*대댓글내용*/}

                        {login == rr.memail && <button type="button" onClick={()=>deltererply(rr.reno)}> 삭제</button> }


                        {/*로그인 한 사람과 댓글 작성자가 일치해야 삭제 버튼이 보여짐*/}
                    </div>{/*rereplyWrap*/}

                </>
                )
            })
        }
        </div>
    )
}