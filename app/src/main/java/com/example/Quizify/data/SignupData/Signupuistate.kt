package com.example.Quizify.data.SignupData

data class Signupuistate(
    var name:String="",
    var email: String="",
    var phone:String="",
    var password:String="",
    var privacypolicyaccepted:Boolean=false,

    var nameerror:Boolean=false,
    var emailerror:Boolean=false,
    var phoneerror:Boolean=false,
    var passworderror:Boolean=false,
    var privacycheckboxerror:Boolean=false
    )
