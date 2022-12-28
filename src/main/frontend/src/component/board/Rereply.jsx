import React , { useState , useEffect } from "react";
import axios from "axios";


export default function Rereply(porps){

    return(
        <div>{
                porps.data.map( (rr) => {
                    return (
                        <div> {rr.rercomment} </div>
                    )
                })
            }
        </div>
    )
}