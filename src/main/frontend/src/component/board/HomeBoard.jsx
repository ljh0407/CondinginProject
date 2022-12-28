import React,{ useState,useEffect } from 'react';
import axios from 'axios';
import css from '../../css/Board/homeBoard.css';
//카테고리별로 가져올 게시물

/*-------*/
/* 2.전역변수 */
/*--------- */

// 3.컴포넌트[함수] 만들기
export default function HomeBoard( props ){

    const [ login , setLogin ] = useState({}); // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링

    axios
        .get("/member/getloginMno")
        .then( (response) => { setLogin( response.data ); } )

    const [getdesclist,setGetdesclist] = useState([
        { rno : 0 ,  btitle : "" , memail : ""}
    ]); //최신글 가져오기 12.23 최예은
    useEffect( ()=>{
        axios
            .get("/board/getdesclist" , { params : { cno : props.cno }} )
            .then(res=>{setGetdesclist(res.data); console.log(res.data)})
            .catch(err=>{console.log(err);})
    } ,[]  );

    //글 상세보기
    const loadView=(bno)=>{
        axios
            .get("/board/setview" ,{params:{bno:bno}})
        window.location = "/board/bview/"+bno
    }

    /* ------------ 5. html or jsx표현식 { }------------------*/
    return(
        <div className="blist">
            <h3> <a href={"/board/"+props.cno} style={{textDecoration: "none",color:"#000"}}> { props.bcname} </a> </h3>

            {
                getdesclist.map( (b) => {
                    return (
                        <div className="sectionWrap">
                            <div className="getViewSection">{/*상단 프로필 사진, 작성자, 리플수 및 조회수 및 좋아요 수 리플수는 없어질 수도 있습니다. */}
                                <div className="memberInfor">
                                    <img className="profile" src={"/static/media/"+ b.mprofile } /> {/* 게시글 작성자의 프로필 */}
                                    <li> { b.mnick == null ? b.memail : b.mnick }  </li>    {/* 닉네임없으면 이메일 표시 */}
                                </div>
                                <div className="countSection">
                                    <span className="bgood"> { b.bgood} {/*좋아요수*/} </span>
                                    <span className="bview"> { b.bview} {/*조회수*/} </span>
                                    <span className="bdate"> { b.bdate} {/*작성시간*/} </span>
                                </div>
                            </div>
                            <div className="titleSection">  {/* 선택한 제목의 상세 게시글 보기 */}
                                <span className="btitle" onClick={ () => loadView(b.bno) }> { b.btitle} {/*제목*/} </span>
                            </div>
                            {/*<span className="bno"> { b.bno} {/*bno번호</span>*/}
                            <span className="replecount"> {/*리플개수는 못가져오나?*/} </span>
                        </div>
                    );
                })
            }
        </div>
    )
}