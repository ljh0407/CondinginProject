import React , { useState , useEffect } from "react";
import axios from "axios";
import css from "../../css/Reply/Rereply.css"



const deltererply=(reno)=>{
    alert(reno)
    console.log(reno)
    axios
        .delete("/reply/deleterereply",{ params : { "reno" : reno }})
        .then(res=>{
            if(res.data==true){alert("댓글삭제성공!")}
            else{alert("댓글삭제실패")}
        })
        .catch(err=>{console.log(err)})
}


export default function Rereply(porps){
    return(
        <div>{
            porps.data.map( (rr) => {
                return (
                <>
                    <div className="rereplyWrap">
                        <div className="rereplyProfileImg">{/*대댓글작성자의 프로필이미지*/}
                            <img src={"/static/modia/"+rr.bfilename}/>
                        </div>
                        <div>
                            <div>{rr.memail}</div>{/*작성자..왜 null값이 들어올까요?*/}
                            <div>{rr.bdate}</div>
                        </div>
                        <div> 대댓글내용 :  {rr.rercomment} </div>{/*대댓글내용*/}
                    </div>{/*rereplyWrap*/}
                    rero :{rr.reno}
                    <button type="button" onClick={()=>deltererply(rr.reno)}> 삭제</button>{/*로그인 한 사람과 댓글 작성자가 일치해야 삭제 버튼이 보여짐*/}
                </>
                )
            })
        }
        </div>
    )
}