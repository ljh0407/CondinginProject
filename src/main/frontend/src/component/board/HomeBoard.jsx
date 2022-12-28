/* 1.임포트 */
import React,{ useState,useEffect } from 'react';
import axios from 'axios'; // 12.19 추가
import css from '../../css/Board/homeBoard.css'; // 12.23 최예은 css추가
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
            <div className="titlebox">
                <h3 className="category"> <a href={"/board/"+props.cno} style={{textDecoration: "none",color:"#000"}}> { props.bcname} </a> </h3>
            </div>
            {
                getdesclist.map( (b) => {
                    return (
                        <div className="sectionWrap">
                            <div className="getViewSection">{/*상단 프로필 사진, 작성자, 리플수 및 조회수 및 좋아요 수 리플수는 없어질 수도 있습니다. */}
                                <div className="memberInfor">
                                    <img className="profile" src={"/static/media/"+ b.mprofile } />
                                    <li> { login.mnick == null ? login.memail : login.mnick }  </li>
                                </div>
                                <div className="countSection">
                                    {/*<span className="bgood"> { b.bgood} 좋아요수 </span>*/}
                                    <span className="bview"> <span className="bviewText">조회수 : </span> { b.bview} {/*조회수*/} </span>
                                    <span className="bdate"> { b.bdate} {/*작성시간*/} </span>
                                </div>
                            </div>
                            <div className="titleSection">
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
    /* -------------------------------------------*/
}