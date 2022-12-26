import React , { useState , useEffect } from "react";
import axios from "axios";

export default function Letterlist(porps){

    const [ Letter , setLetter ] = useState( {list:[]})

    function getletter()  {
        axios
            .get("/letter/getlist")
            .then( re => {
                console.log("쪽지리스트");
                console.log( re.data );
                setLetter(re.data)
            })

    }

    useEffect( getletter , [] );

    return(

        Letter.list.map( (l) => {
            return(
                <div>
                    <h6>메시지 출력</h6>
                    <table>
                        <tr>
                            <td>{l.lfrom}</td>
                            <td>{l.ldata}</td>
                        </tr>
                    </table>
                </div>
            )
        })
    )
}