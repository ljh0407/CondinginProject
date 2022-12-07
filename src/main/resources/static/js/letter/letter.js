alert('쪽지확인')


// 글쓰기 이종훈
function setletter(){
  let data = {
        ltitle : document.querySelector(".ltitle").value,
        lcontent : document.querySelector(".lcontent").value,
        lfile : document.querySelector(".lfile").value
   }
    $.ajax({
        url : '/letter/setletter',
        type : 'post',
        data : JSON.stringify(data),
        contentType : 'application/json',
        success : function(re){
            if( re == true){
            console.log(data)
            alert('쪽지보내기성공')
            }else{
                alert('쪽지보내기실패')
                     }
            }
   })
}  // 메소드 end


// 글 리스트출력
function letterList(){
    alert("리스트출력")

    $.ajax({
    url : "/letter/getlist",
    type : 'get',
    success : function(re){
        alert(re)
        let html = ' <tr>'+
                                  '<td>번호</td><td>제목</td> <td>내용 </td>'+
                            '</tr>';
       re.forEach( (l) => {
            html +=  ' <tr>'+
                                    '<td>'+l.lno+'</td><td>'+l.ltitle+'</td> <td>'+l.lcontent+'</td>'+
                             '</tr>';
            })
            document.querySelector('.listtable').innerHTML = html;
        }
    })
}