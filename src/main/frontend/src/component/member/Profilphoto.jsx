import React, {useEffect, useState} from "react";
import axios from "axios";

export default function Profilphoto(props){
    const [member ,setMember] = useState([]);

    useEffect(
        () => axios.get("/member/upprofile").then(res => {setMember(res.data); console.log("프로필"+res.data)},[]).catch(err => console.log('출력오류 : ' + err))
    ,[])

    const [memberdto , setMemberDto] = useState({list:[]})
    const mupdate = () => {alert('클릭'); window.location.href="/member/mupdate"}

    return(
       <div>
           <table className="mlist">
               {
                   memberdto.list.map((m) => {
                       return(
                           <tr>
                               <td>{m.memail}</td>
                               <td>{m.mnick}</td>
                               <td>{m.mprofile}</td>
                               <button type="button" onClick={mupdate}>My Home</button>
                           </tr>
                        )
                   })
               }
           </table>
       </div>
    );
}