package com.example.Quizify.data.loginData

data class Loginuistate(
    var email:String="",
    var password:String="",

    var emailerror:Boolean=false,
    var passworderror:Boolean=false
)
