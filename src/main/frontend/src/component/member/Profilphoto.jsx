import React, {useEffect, useState} from "react";
import axios from "axios";

export default function Profilphoto(props){

    const [member , setMember] = useState([])
    useEffect(
        () => axios.get("/member/upprofile").then(res => {setMember(res.data); console.log('확인 : '+res.data)}).catch(err => console.log('출력오류 : '+err))
    ,[])
    const [memberdto , setMemberDto] = useState({list : []})

    return(
        <table className="mlist">
            {
                memberdto.list.map((m)=>{
                    return(
                        <tr>
                            <td>{m.mnick}</td>
                        </tr>
                    )
                })
            }
        </table>
    );
}