$(document).ready(function(){
    console.log("ready for go...");
    /*$("#editdetails").click(function(){
        $("#viewInfo").hide();
        $("#editInfo").show();
    })


    $("#editdetails2").click(function(){
        $("#viewInfo").show();
        $("#editInfo").hide();
    })*/


})


function validUserDetails() {

    if($("#name").val()==""){
        $("#name").focus();
        document.getElementById('name').style.borderColor = "red";
        return false;
    }

    if($("#dob").val()==""){
        $("#dob").focus();
        document.getElementById('dob').style.borderColor = "red";
        return false;
    }

    if($("#blood").val()==""){
        $("#blood").focus();
        document.getElementById('blood').style.borderColor = "red";
        return false;
    }

    if($("#gender").val()==""){
        $("#gender").focus();
        document.getElementById('gender').style.borderColor = "red";
        return false;
    }

    if($("#city").val()==""){
        $("#city").focus();
        document.getElementById('city').style.borderColor = "red";
        return false;
    }

    if($("#contact").val()==""){
        $("#contact").focus();
        document.getElementById('contact').style.borderColor = "red";
        return false;
    }

    if($("#relation").val()==""){
        $("#relation").focus();
        document.getElementById('relation').style.borderColor = "red";
        return false;
    }

    if($("#bio").val()==""){
        $("#bio").focus();
        document.getElementById('bio').style.borderColor = "red";
        return false;
    }
}
