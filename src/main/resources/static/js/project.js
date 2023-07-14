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

let email3=document.querySelector("#email3")

// 우편번호 검색 모달창 띄우기
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

// 검색한 우편번호 결과 출력하기
const showzipaddr = (jsons) => {
    jsons=JSON.parse(jsons); //문자열을 json객체로 변환
    let addr="";
    jsons.forEach(function (data, idx) {
       let bunji=(data['bunji'] !== null) ? data['bunji'] : '';
       addr+=`<option>${data['zipcode']} ${data['sido']} ${data['gugun']}
                ${data['dong']} ${bunji}</option>`;
    });
    addrlist.innerHTML=addr;
};

// 우편번호 검색하기
fzipbtn?.addEventListener('click', ()=>{
    if(dong.value==='') {
        alert("동 이름을 입력하세요.");
        return;
    }
    const url="/join/zipcode?dong="+dong.value;
    fetch(url).then(response=>response.text())
        .then(text=>showzipaddr(text));
});

// 주소 선택한 뒤 닫기
sendzip?.addEventListener('click', ()=>{
    let frm=document.forms.joinfrm;
    let addr=addrlist.value;
    if(addr != "") {
        let zip=addr.split(" ")[0];
        let addrs=addr.split(" ");
        let vaddr=`${addrs[1]} ${addrs[2]} ${addrs[3]}`;
        frm.zip1.value=zip.split("-")[0];
        frm.zip2.value=zip.split("-")[1];
        frm.addr1.value=vaddr;

        modal.hide();
    } else {
        alert("주소를 선택하세요.");
    }
});
dong?.addEventListener('keydown', (e) => {
   if (e.keyCode===13) e.preventDefault();
});

// 전자우편 주소 선택한 뒤 닫기
email3?.addEventListener('click', ()=>{
   let frm=document.forms.joinfrm;
   if(email3.value==="직접 입력하기") {
        frm.email2.readOnly=false;
        frm.email2.value="";
   } else if(email3.value!=="선택하세요.") {
        frm.email2.readOnly=true;
        frm.email2.value=email3.value;
   }
});

// 비밀번호 중복 확인
let pwd=document.querySelector("#pwd");
let repwd=document.querySelector("#userpw2");
let pwdmsg=document.querySelector("#upwmsg");

repwd?.addEventListener('blur', ()=>{
    pwdmsg.innerText = "비밀번호가 일치하지 않습니다.";
    pwdmsg.className = 'col-form-label text-danger';
    if(pwd.value === repwd.value) {
        pwdmsg.innerText = "비밀번호가 일치합니다.";
        pwdmsg.className = 'col-form-label text-primary';
    }
});

// 아이디 중복 확인
let userid=document.querySelector("#uid");
let uidmsg=document.querySelector("#uidmsg");
let checkid=document.querySelector("#checkid");

const checkuid =(chkuid) => {
    let umsg="사용할 수 없습니다."
    uidmsg.className='col-form-label text-danger';
    checkid.value="no";
    if(chkuid === "0") {
        umsg="사용할 수 있습니다."
        uidmsg.className='col-form-label text-primary';
        checkid.value="yes";
    }
        uidmsg.innerText=umsg;
}
userid?.addEventListener('blur', ()=>{
   if(userid.value === "") {
       uidmsg.innerText="6~16 자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다."
       checkid.value="no";
       return;
   }
   const url="/join/checkuid/"+userid.value;
   fetch(url).then(response=>response.text())
       .then(text=>checkuid(text));
});

// 회원정보 저장
let joinok=document.querySelector("#joinok");
let frm=document.forms.joinfrm;

joinok?.addEventListener('click',()=>{
    if(frm.userid.value === "") alert("아이디를 입력해주세요.");
    else if(frm.passwd.value === "") alert("비밀번호를 입력해주세요.");
    else if(frm.userpw2.value === "") alert("비밀번호 확인을 입력해주세요.");
    else if(frm.zip1.value === "" || frm.zip2.value === "") alert("우편번호를 입력해주세요.");
    else if(frm.email1.value === "" || frm.email2.value === "") alert("전자우편을 입력해주세요.");
    else if(frm.addr1.value === "" || frm.addr2.value === "") alert("주소를 입력해주세요.");
    else if(frm.phone1.value === "" || frm.phone2.value === "" || frm.phone3.value === "") alert("전화번호를 입력해주세요.");
    else if(grecaptcha.getResponse() === "") alert("자동입력방지를 해주세요.");
    else if(checkuid.value === "no") alert("아이디 중복 확인을 진행하세요.");
    else {
        frm.secret.value=frm.secret1.value+"-"+frm.secret2.value;
        frm.zipcode.value=frm.zip1.value+"-"+frm.zip2.value;
        frm.phone.value=frm.phone1.value+"-"+frm.phone2.value+"-"+frm.phone3.value;
        frm.email.value=frm.email1.value+"@"+frm.email2.value;

        alert("회원가입이 완료되었습니다.");

        frm.method='post';
        frm.submit();
    }
});

// 메인으로 이동
let go2index=document.querySelector("#go2index");
go2index?.addEventListener('click',()=>{
    location.href="/";
});

// 프로젝트 소개로 이동하기 startbtn
let startbtn=document.querySelector("#startbtn");
startbtn?.addEventListener('click', ()=>{
   location.href="/intro"
});

// 회원가입으로 이동하기 joinbtn
let joinbtn=document.querySelector("#joinbtn");
joinbtn?.addEventListener('click', ()=> {
   location.href="/join/agree"
});

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