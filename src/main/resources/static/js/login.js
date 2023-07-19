

// 로그인 하기
let loginbtn=document.querySelector("#loginbtn");
let lgnuid=document.querySelector("#userid");
let lgnpwd=document.querySelector("#passwd");
let lgnfrm=document.querySelector("#lgnfrm");
loginbtn?.addEventListener('click', ()=>{
    if(lgnuid.value==="") {
        alert("아이디를 입력해주세요.")
    } else if (lgnpwd.value==="") {
        alert("비밀번호를 입력해주세요.")
    } else {
        lgnfrm.method="post";
        lgnfrm.action="/join/login";
        lgnfrm.submit();
    }
});

// 로그아웃 하기
let logoutbtn=document.querySelector("#logoutbtn");
logoutbtn?.addEventListener('click', ()=>{
    location.href="/join/logout";
});