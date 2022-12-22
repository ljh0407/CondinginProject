import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

export default function Profilphoto(props){
    const [member ,setMember] = useState([]);

    const [ login , setLogin ] = useState(null); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
    axios
        .get("/member/getloginMno") //url 호출                    언더바 기준으로 자르기(작성자와 로그인한 사람확인)
        .then( (response) => { setLogin( response.data.split("_")[0] );  console.log( login ) } )


    return(

        <div>
            <form className="myinfo">
                Email : <div disabled></div>
                프로필사진 : <div>  </div>

            </form>
        </div>




    )

}

