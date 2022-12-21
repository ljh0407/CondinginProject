import React, {useEffect, useState} from "react";
import axios from "axios";

export default function Profilphoto(){
    const [member ,setMember] = useState([]);

    useEffect(
        () => axios.get("/member/profilelist").then(res => {setMember(res.data)},[])
    )

    const mupdate = () => {alert('클릭'); window.location.href="/member/mupdate"}

    return(
        <div>
            <input type="file" name="mprofile" defaultValue={member.mprofile} />
            <input type="text" name="mnick" defaultValue={member.mnick} />
            <button type="button" onClick={ mupdate }>My Home</button>
        </div>
    );
}