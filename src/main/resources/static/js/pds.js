
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

// 댓글
let cmtfrm=document.querySelector("#cmtfrm");
let newcmtbtn=document.querySelector("#newcmtbtn");

newcmtbtn?.addEventListener('click', ()=>{
    if(cmtfrm.userid.value === '') {
        alert("로그인 해주세요.");
    } else if(cmtfrm.pno.value === '') {
        alert("로그인 해주세요.");
    } else if(cmtfrm.comments.value === '') {
        alert("내용을 작성해주세요.");
    } else {
        cmtfrm.method = "post";
        cmtfrm.action = "/pds/cmt/write";
        cmtfrm.submit();
        alert("작성이 완료되었습니다.");
    }
});

// 대댓글
let modal=null;
const refno=document.querySelector("#ref");
const showReply = (ref) => {
    refno.value = ref; // 대댓글을 작성할 댓글의 번호를 알아내기
    modal = new bootstrap.Modal(replyModal, {});
    modal.show();
};

const replybtn=document.querySelector("#replybtn");
const frm=document.querySelector("#replyfrm");
replybtn?.addEventListener('click', ()=>{
    if(frm.comments.value === '') alert("내용을 작성해주세요.");
    if(frm.ref.value === '') alert("댓글이 없습니다.");
    if(frm.pno.value === '') alert("본문이 없습니다.");
    else {
        frm.method="post";
        frm.action="/pds/reply/write";
        frm.submit();
    }
});