import React, {useEffect, useState} from "react";
import axios from "axios";

export default function Profilphoto(props){
    const [member ,setMember] = useState([]);

    useEffect(
        () => axios.get("/member/profile").then(res => {setMember(res.data)},[]).catch(err => console.log('출력오류 : ' + err))
    )
    //
    const mupdate = () => {alert('클릭'); window.location.href="/member/mupdate"}

    return(
        <div>
            <div>{member.memail}</div>   {/*작성자*/}
            <div>{member.mnick}</div>   {/*작성자*/}
            <div>{member.mprofile}</div> {/*프로필*/}
            <button type="button" onClick={ mupdate }>My Home</button>
        </div>
    );
}