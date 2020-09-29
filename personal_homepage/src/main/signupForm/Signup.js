
// 변수 선언

let id = document.querySelector("#id");


let pwd1 = document.querySelector("#pwd1");
let pwdMsg = document.querySelector("#alertTxt");
let pwdImg1 = document.querySelector("#pwd1_img");


let pwd2 = document.querySelector("#pwd2");
let pwdImg2 = document.querySelector("#pwd2_img");
let pwdMsgArea = document.querySelector(".int_pass");

let userName = document.querySelector("#name");

let yy = document.querySelector("#yy");
let mm = document.querySelector("#mm");
let dd = document.querySelector("#dd");

let gender = document.querySelector("#gender");

let email = document.querySelector("#email");

let mobile = document.querySelector("#mobile");

let error = document.querySelectorAll(".error_next_box");


// 이벤트 헨들러 연결

id.addEventListener("focusout", checkId);

pwd1.addEventListener("focusout", checkPwd);
pwd2.addEventListener("focusout", comparePwd);

userName.addEventListener("focusout", checkName);

yy.addEventListener("focusout", isBirthCompleted);
mm.addEventListener("focusout", isBirthCompleted);
dd.addEventListener("focusout", isBirthCompleted);

gender.addEventListener("focusout", function() {
    if (gender.value === "성별") {
        error[5].style.display = "block";
    } else {
        error[5].style.display = "none";
    }
});

email.addEventListener("focusout", isEmailCorrect);

mobile.addEventListener("focusout", checkPhoneNum);



// 콜벡 함수

function checkId() {
    let idPattern = /[a-zA-Z0-9_-]{5,20}/;
    if (id.value === "") {
        error[0].innerHTML = "필수 정보입니다.";
        error[0].style.color = "#E53A40";
        error[0].style.display = "block";
    } else if (!idPattern.test(id.value)) {
        error[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호 _ , - 만 사용이 가능합니다.";
        error[0].style.color = "#E53A40";
        error[0].style.display = "block";
    } else {
        error[0].innerHTML = "Great!";
        error[0].style.color = "#08A600";
        error[0].style.display = "block";
    }
}

function checkPwd() {
    let pwdPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
    if (pwd1.value === "") {
        error[1].innerHTML = "필수 정보입니다.";
        error[1].style.color = "#E53A40";
        error[1].style.display = "block";
    } else if (!pwdPattern.test(pwd1.value)) {
        error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
        pwdMsg.innerHTML = "사용불가";
        pwdMsgArea.style.paddingRight = "93px";
        error[1].style.display = "block";

        pwdMsg.style.display = "block";
        pwdImg1.src = ""
    } else {
        error[1].innerHTML = "Great!";
        error[1].style.color = "#08A600";
        error[1].style.display = "block";
    }
}

function comparePwd() {

}













