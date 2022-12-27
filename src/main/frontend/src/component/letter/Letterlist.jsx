import React , { useState , useEffect } from "react";
import axios from "axios";


export default function Letterlist(porps){

    const [ LetterList , setLetterList ] = useState( [])

    function getletter()  {
        axios
            .get("/letter/fromletter")
            .then( re => {
                console.log( '쪽지리스트 : '+re.data );
                setLetterList(re.data);
            })
            .catch(err => {console.log('리스트 오류'+err);})
    }

    useEffect(getletter , [] );

    return(
    <div>
        <h3> 보낸 쪽지 함 </h3>
        <table>
            {
                LetterList.map( (l) => {
            return(
                <tr>
                    <td>{l.lcontent}</td>
                </tr>
            )

        })
            }
        </table>
    </div>
    )
}