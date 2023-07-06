// agree
let agree1=document.querySelector("#agree1");
let agree2=document.querySelector("#agree2");
let agreeok=document.querySelector("#agreeok");
let agreeno=document.querySelector("#agreeno");

agreeok?.addEventListener('click',()=>{
    if(!agree1.checked) alert("이용약관에 동의해주세요.");
    else if (!agree2.checked) alert("개인정보 수집 및 이용에 동의해주세요.");
    else location.href="/join/check";
});
agreeno?.addEventListener('click',()=>{
    location.href="/";
});

