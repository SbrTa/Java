$(document).ready(function(){


})

function loginValidate() {
   if($("#userName").val()==null){
        $("#userName").focus();
        document.getElementById('userName').style.borderColor = "red";
        return false;
    }

    if($("#password").val()==""){
        $("#password").focus();
        document.getElementById('password').style.borderColor = "red";
        return false;
    }
}