// alert('연결')
// getloginMno()
// function getloginMno(){
//     $.ajax({
//     url : "/member/getloginMno",
//     type : "get",
//     success : function(re) {
//         alert(re);
//         let top_menu = ' ';
//         if( re == ""){ // 로그인 안했다
//             top_menu +=
//                              '<a href="/member/signup"><button type="button" onclick="">회원가입</button></a>'+
//                              '<a href="/member/login"><button type="button"onclick="">로그인</button></a>'
//         }else{ // 로그인 했을때
//              top_menu +=
//                                    ' <a href="/member/logout"> <button type="button">로그아웃</button></a>'+
//                                    '<a href="/member/signup"> <button type="button" onclick="">비밀번호찾기</button></a>'+
//                                    '<a href="/member/findpassword"><button type="button" onclick="">비밀번호수정</button></a>'+
//                                    ' <a href="/member/delete"> <button type="button" onclick="">회원탈퇴</button></a>'
//
//         }
//         document.querySelector("#top_menu").innerHTML = top_menu ;
//         }
//     })
// }
//
//  function logout(){
//  $.ajax({
//     url : "/member/logout",
//     type : "get",
//     success : function(re) {
//     location.href="/";  // location.href= URL
//         }
//     })
//  }