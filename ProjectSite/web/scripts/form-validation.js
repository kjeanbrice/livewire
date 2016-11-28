/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ErrEmail = "This is not a valid email address.";
var ErrFirstName = "Your first name can't be empty.";
var ErrLastName = "Your last name can't be empty.";
var ErrPasswordLength = "The password you entered is not valid.";
var ErrPasswordVerify = "The passwords do not match.";
var ErrDisplayName = "The display name is not valid. Please choose another.";
var ErrMessageGeneric = "This field can not be empty.";
var ErrSecurityAnswer = "You must have an answer to your security question.";
var ErrSecurityQuestion = "You must select a security question.";
var ErrLanguage = "You must select a language.";
var ErrCountry = "Please select a country";


var firstName = document.getElementById("txt-firstname");
var lastName = document.getElementById("txt-lastname");
var emailAddress = document.getElementById("txt-email");
var password = document.getElementById("txt-password");
var verifyPassword = document.getElementById("txt-verify-password");
var displayName = document.getElementById("txt-display-name");
var securityQuestions = document.getElementById("select-security-question");
var country = document.getElementById("select-country");
var securityAnswer = document.getElementById("txt-security-answer");
var language = document.getElementById("select-language");



/*emailAddress.addEventListener("input",validateEmailAddress);
 password.addEventListener("input",validatePassword);
 verifyPassword.addEventListener("input",validateVerifyPassword);
 displayName.addEventListener("input",validateDisplayName);
 securityAnswer.addEventListener("input",validateSecurityAnswer);
 securityAnswer.addEventListener("select",validateSecurityQuestion);*/


/*var validationKey = ["emailAddress",
 "password",
 "verifyPassword",
 "displayName",
 "firstName",
 "lastName"
 ];
 
 var validationArray = {
 "emailAddress":false,
 "password":false,
 "verifyPassword":false,
 "displayName":false,
 "firstName":false,
 "lastName":false
 };*/


$(function () {

    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});


function validateFirstName() {
    var errMessage = document.getElementById("err-firstname");
    var firstNameValue = firstName.value.trim();

    if (firstNameValue.length > 0) {
        errMessage.style.display = "none";
    }
    else {
        errMessage.innerHTML = "<br>" + ErrFirstName;
        errMessage.style.display = "inline";
    }
}

function validateLastName() {
    var errMessage = document.getElementById("err-lastname");
    var lastNameValue = lastName.value.trim();

    if (lastNameValue.length > 0) {
        errMessage.style.display = "none";
    }
    else {
        errMessage.innerHTML = "<br>" + ErrLastName;
        errMessage.style.display = "inline";
    }
}


function validatePassword()
{
    var errMessage = document.getElementById("err-password");
    var passwordInput = password.value.trim();

    if (passwordInput.length >= 0 && passwordInput.length < 8) {

        errMessage.innerHTML = ErrPasswordLength;
        errMessage.style.display = "inline";
    }

    if (passwordInput.length >= 8) {
        errMessage.style.display = "none";
        validationArray["password"] = true;
        return true;
    }

    /*validationArray["password"] = false;
     console.log(validationArray["password"]);*/
    return false;
}

function validateVerifyPassword() {

    var errMessage = document.getElementById("err-verify-password");
    var passwordInput = verifyPassword.value.trim();

    if (passwordInput.length >= 0 && passwordInput.length < 8) {

        errMessage.innerHTML = ErrPasswordLength;
        errMessage.style.display = "inline";
    }

    if (passwordInput.length >= 8) {
        if (password.value.length >= 8 && (password.value.trim() === passwordInput)) {
            errMessage.style.display = "none";
            validationArray["verifyPassword"] = true;
            return true;
        }
        errMessage.innerHTML = ErrPasswordVerify;
        errMessage.style.display = "inline";
    }

    /*validationArray["verifyPassword"] = false;*/
    return false;
}

function validateEmailAddress() {
    var errMessage = document.getElementById("err-emailaddress");
    var email = emailAddress.value.trim();
    if (email.length > 0 && email.indexOf("@") > -1) {
        errMessage.style.display = "none";
        validationArray["verifyEmail"] = true;
    }
    else {
        errMessage.innerHTML = "<br><br>" + ErrEmail;
        errMessage.style.display = "inline";
        validationArray["verifyEmail"] = false;
    }
}

var hr = new XMLHttpRequest();
function validateCountry() {


    var fn = country.value.trim();
    var url = "http://localhost:8080/ProjectCSE336/assignment4?country_value=" + fn;

    hr.open("GET", url, true);
    hr.onreadystatechange = countryValidation;
    hr.send(null);
}

function countryValidation() {
    var errMessage = document.getElementById("err-select-country");
    if (hr.readyState === 4 && hr.status === 200) {
        var return_data = hr.responseText;
        if (return_data === "valid") {
            errMessage.style.display = "none";
        }
        else {
            errMessage.innerHTML = "<br>" + return_data;
            errMessage.style.display = "inline";
        }
    }
}


function validateLanguage() {
    var languageValue = language.value.trim();
    var errMessage = document.getElementById("err-select-language");

    if (languageValue === "none") {
        errMessage.innerHTML = "<br>" + ErrLanguage;
        errMessage.style.display = "inline";
    }
    else {
        errMessage.style.display = "none";
    }
}


function validateDisplayName() {
    var errMessage = document.getElementById("err-display-name");
    var display_name = displayName.value.trim();

    if (display_name.length > 2 && display_name.length < 32) {
        errMessage.style.display = "none";
    }
    else {
        errMessage.innerHTML = "<br><br>" + ErrDisplayName;
        errMessage.style.display = "inline";
    }
}

function validateSecurityAnswer() {
    var errMessage = document.getElementById("err-security-answer");
    var answer = securityAnswer.value.trim();

    if (answer.length > 0) {
        errMessage.style.display = "none";
    }
    else {
        errMessage.innerHTML = "<br><br>" + ErrSecurityAnswer;
        errMessage.style.display = "inline";
    }

}


function validateSecurityQuestion() {
    var errMessage = document.getElementById("err-select-security-question");
    var securityAnswerValue = securityQuestions.value;

    if (securityAnswerValue === "") {
        errMessage.innerHTML = ErrSecurityQuestion;
        errMessage.style.display = "inline";
    }
    else {
        errMessage.style.display = "none";
    }

}



