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
let check=document.querySelector("#check");
let checkok2=document.querySelector("#checkok2");
let checkno2=document.querySelector("#checkno2");

checkok2?.addEventListener('click', ()=>{
    let checkfrm2 = document.forms.checkfrm2;

    if(checkfrm2.name2.value == "") alert("이름을 입력해주세요.");
    else if(checkfrm2.secret1.value == "") alert("주민등록번호를 입력해주세요.");
    else if(checkfrm2.secret2.value == "") alert("주민등록번호를 더 입력해주세요.");
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



// join
let fzipbtn=document.querySelector("#find");
let zipbtn=document.querySelector("#zipbtn");
let dong=document.querySelector("#dong");
let zipmodal=document.querySelector("#zipmodal")
let addrlist=document.querySelector("#addrlist");
let sendzip=document.querySelector("#send");
let modal=null;
zipbtn?.addEventListener('click', ()=>{
    while(addrlist.lastChild) {
        addrlist.removeChild(addrlist.lastChild);
        // removeChild 하위 요소만 지우는 기능
        // 이전 검색 결과 지우기
    }
    dong.value="";  // 이전 검색 키워드 지우기

    try {
        // 새로운 모달 생성
        modal=new bootstrap.Modal(zipmodal, {});
    } catch (e) { }
        modal.show();
});
const showzipaddr = (jsons) => {
    jsons=JSON.parse(jsons); //문자열을 json객체로 변환
    let addr="";
    jsons.forEach(function (data, idx) {
       addr+=`<option>${data['zipcode']} ${data['sido']} ${data['gugun']}
                ${data['dong']} ${data['bunji']}</option>`;
    });
    addrlist.innerHTML=addr;
};
fzipbtn?.addEventListener('click', ()=>{
    if(dong.value==='') {
        alert("동 이름을 입력하세요.");
        return;
    }
    const url="/join/zipcode?dong="+dong.value;
    fetch(url).then(response=>response.text())
        .then(text=>showzipaddr(text));
});


sendzip?.addEventListener('click', ()=>{
    let frm=document.forms.joinfrm1;
    let addr=addrlist.value;    // 선택한 주소 항목
    if(addr != "") {
        let zip=addr.split(" ")[0];
        let vaddr=`${addr.split(" ")[1]} ${addr.split(" ")[2]} ${addr.split(" ")[3]}`;
        frm.zip1.value=zip.value("-")[0];
        frm.zip2.value=zip.value("-")[1];
        frm.addr1.value=vaddr;

        modal.hide();
    } else {
        alert("주소를 선택하세요.")
    }
});