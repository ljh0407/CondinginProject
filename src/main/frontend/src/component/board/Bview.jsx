import React from 'react';
import axios from "axios";
//테스트?
export default function Bview(){

    return(
        <div className="wrap">
            <h2>개별글 보기 페이지 입니다.</h2>

            <div className="boatdWrap">

                <div className="memberSection">
                    <div className="mprofile"> 프로필</div>
                    {/*프로필*/}
                    <div>
                        <span className="mnick">닉네임</span> {/*닉네임*/}
                        <span className="data">올린시간</span>

                    </div>
                </div>
                <div className="boardsection">
                    <div className="btitle">제목</div>
                    <div className="bcontent">내용</div>
                </div>

                <div className="updown">
                    <button type="button" className="likeno" onClick="likeno()">좋아요</button>
                    <button type="button" className="dno" onClick="dno()">싫어요</button>
                </div>

                <div className="replySection"> {/*댓글작성을 하기 위해서는 로그인을 해야합니다.*/}

                    <div className="mprofile">로그인한사람의 프로필사진</div>
                    <textarea className="rcomment"> </textarea>
                </div> //replySection
            </div> {/*boatdWrap*/}
        </div>
    )
}










