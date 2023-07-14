
// 자료실
let newpdsbtn=document.querySelector("#newpdsbtn");
let modpdsbtn=document.querySelector("#modpdsbtn");
let rmvpdsbtn=document.querySelector("#rmvpdsbtn");
let lstpdsbtn=document.querySelector("#lstpdsbtn");

newpdsbtn?.addEventListener('click', ()=>{
    location.href="/pds/write";
});
modpdsbtn?.addEventListener('click', ()=>{
    if(confirm("글을 수정하시겠습니까?")) {
        alert("지원되지 않는 기능입니다.")
    }
});

let remove=document.querySelector("#remove");
rmvpdsbtn?.addEventListener('click', ()=>{
    if(confirm("글을 삭제하시겠습니까?")) {
        location.href="/pds/delete/"+remove.value
    }
});
lstpdsbtn?.addEventListener('click', ()=>{
    location.href="/pds/list/1"
});


// 글쓰기
let wrtpdsbtn=document.querySelector("#wrtpdsbtn");
let pdsfrm=document.querySelector("#pdsfrm");
wrtpdsbtn?.addEventListener('click', ()=>{
    if(pdsfrm.title.value==="") {
        alert("제목을 입력해주세요.")
    } else if (pdsfrm.contents.value==="") {
        alert("본문을 입력해주세요.")
    } else if(grecaptcha.getResponse() === "") {
        alert("자동입력방지를 해주세요.");
    } else {
        pdsfrm.method = "post";
        pdsfrm.enctype="multipart/form-data"; //파일 첨부 기능을 위해 추가
        pdsfrm.submit();
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
        location.href=`/pds/find/${findtype.value}/${findkey.value}/1`;
    }
});