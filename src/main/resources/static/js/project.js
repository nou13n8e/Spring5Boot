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

// check
let name2=document.querySelector("#name2");
let secret1=document.querySelector("#secret1");
let secret2=document.querySelector("#secret2");
let check=document.querySelector("#check");
let checkok2=document.querySelector("#checkok2");
let checkno2=document.querySelector("#checkno2");

checkok2?.addEventListener('click', ()=>{
    let checkfrm2 = document.forms.checkfrm2;

    if(checkfrm2.name2.value == "") alert("이름을 입력해주세요.");
    else if(checkfrm2.secret1.value == "") alert("주민등록번호를 입력해주세요.");
    else if(checkfrm2.secret2.value == "") alert("주민등록번호를 입력해주세요.");
    else if(!check.checked) alert("주민등록번호 처리에 동의해주세요.");
    else {
        // let param= "?name=" + checkfrm2.name2.value;
        // param += "&secret1=" + checkfrm2.secret1.value;
        // param += "&secret2=" + checkfrm2.secret2.value;
        // location.href="/join/join" + param;
        checkfrm2.method="post";
        checkfrm2.submit();
    }
});
checkno2?.addEventListener('click', ()=>{
    location.href="/"
});