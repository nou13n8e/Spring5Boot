
// 게시판
let newbdbtn=document.querySelector("#newbdbtn");
let modbdbtn=document.querySelector("#modbdbtn");
let rmvbdbtn=document.querySelector("#rmvbdbtn");
let lstbdbtn=document.querySelector("#lstbdbtn");

newbdbtn?.addEventListener('click', ()=>{
    location.href="/board/write";
});
modbdbtn?.addEventListener('click', ()=>{
    if(confirm("글을 수정하시겠습니까?")) {
        alert("지원되지 않는 기능입니다.")
    }
});

let remove=document.querySelector("#remove");
rmvbdbtn?.addEventListener('click', ()=>{
    if(confirm("글을 삭제하시겠습니까?")) {
        location.href="/board/delete/"+remove.value
    }
});
lstbdbtn?.addEventListener('click', ()=>{
    location.href="/board/list/1"
});


// 글쓰기
let wrtbdbtn=document.querySelector("#wrtbdbtn");
let bdfrm=document.querySelector("#bdfrm");
wrtbdbtn?.addEventListener('click', ()=>{
    if(bdfrm.title.value==="") {
        alert("제목을 입력해주세요.")
    } else if (bdfrm.contents.value==="") {
        alert("본문을 입력해주세요.")
    } else if(grecaptcha.getResponse() === "") {
        alert("자동입력방지를 해주세요.");
    } else {
        bdfrm.method = "post";
        bdfrm.submit();
        alert("작성이 완료되었습니다.")
    }
});

// 검색 기능
let findbtn=document.querySelector("#findbtn");
let findtype=document.querySelector("#findtype");
let findkey=document.querySelector("#findkey");

findbtn?.addEventListener('click', ()=>{
    if(findkey.value === '') {
        alert("검색어를 입력해주세요.");
    } else {
        location.href=`/board/find/${findtype.value}/${findkey.value}/1`;
    }
});