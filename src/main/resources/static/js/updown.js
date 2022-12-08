alert('123')

function setupdown(){

    let data= {

       likeno : document.querySelector('.likeno').value,
       dno: document.querySelector('.dno').value,
    }
    $.ajax({
        url:"/board/postupdown",
        type:"post",
        data : JSON.stringify(data),
        contentType : "application/json",
        success: function(re){
            if(re==true){
            alert('좋아요');

            }
            else{alert('싫어요')}
            }
    })
}