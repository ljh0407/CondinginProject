import React , { useState , useEffect } from "react";
import axios from "axios";


export default function Letterlist(porps){

    const [ LetterList2 , setLetterList2 ] = useState( [])

    function getletter2()  {
        axios
            .get("/letter/toletter")
            .then( re => {
                console.log( '쪽지리스트 : '+re.data );
                setLetterList2(re.data);
            })
            .catch(err => {console.log('리스트 오류'+err);})
    }

    useEffect(getletter2 , [] );

    return(
        <div>
            <h3> 받은 쪽지 함 </h3>
            <table>
                {
                    LetterList2.map( (l) => {
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