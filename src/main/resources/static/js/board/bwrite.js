//12.7 최예은 글쓰기 테스트
alert("bwrite.js 확인")
/*썸머노트*/
$(document).ready(function() {
  $('#summernote').summernote({height: 300});
});

/*/////////////////////////////글쓰기/////////////////////////////*/
function bwrite(){
    alert("글 작성하기")


    // 한꺼번에 전송하기 위해 객체로 만들어서 가져온다.

    let data = {
        btitle : document.querySelector(".btitle").value,
        bcontent: document.querySelector(".bcontent").value,
    }

    console.log("bwrite.js 확인" + data)
    console.log(data)

    $.ajax({
        url: "/board/bwrite",
        type:"POST",
        data:JSON.stringify(data),
        contentType:"application/json",
        success : function(re){
            alert(re)
        }
    })
}

/////////////////////////////글출력/////////////////////////////

blist() // blist 실행
function blist(){
    $.ajax({
        url: "/board/blist",
        type:"Get",
        contentType:"application/json",
        success : function(list){
            alert(list)
            let html = '';
               console.log(html)

               list.forEach((b)=>{
                    html +=
                        "<li class='blist'>"+
                            "<div class='profile'>"+
                                "<div class='mprofile'> 프로필가져와야함</div>"+ //프로필사진
                            "</div>"+
                                "<div  class='boardSection'>"+
                                    "<div class='writeNview'>"+
                                        "<span class='bwrite'>" +b.bwrite+ "</span>"+ //작성자
                                        "<span class='bdate'>작성한시간이 올라가야함</span>"+ //작성한시간
                                    "</div>"+
                                    "<div class='title'>"+
                                        "<a class='btitle' onclick='getboard("+b.bno+")'>" +b.btitle+ "</a>"+ //글제목
                                    "</div>"+
                                "</div>"+
                                "<div>"+//조회수 및 좋아요
                                    "<span class='bview'>"+b.bview+"</span>"+ //조회수
                                    "<span class='bgood'>"+b.bgood+"</span>"+ // 좋아요수
                                "</div>"+
                        "</li>"
               })//forEach end
               document.querySelector(".blist-ul").innerHTML = html;
        }//success end
    })//ajax end
}//blist end*/


/*
     //추후에 첨부파일을 보내기 위해 미리 form 전체 보낼 소스 미리 작성함

    /*
    //form 전체를 가져옴
    let boardform = document.querySelector(".boardform")
        console.log("bwrite.js boardform 확인")
        console.log(boardform)
    let formdata = new FormData(boardform)
        console.log("bwrite.js formdata 확인")
        console.log(formdata)

    $.ajax({
        url :"/bwrite" ,
        data : "formdata",
        type : "post",
        contentType : false,
        processData : false,
        success: function( re ){ alert(re)}
    })

    */


