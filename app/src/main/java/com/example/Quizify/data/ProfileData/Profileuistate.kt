package com.example.Quizify.data.ProfileData

data class Profileuistate(
    var name:String="",
    var email: String="",
    var phone:String="",
    var dob:String="",

    var nameerror:Boolean=false,
    var emailerror:Boolean=false,
    var phoneerror:Boolean=false,
    var doberror:Boolean=false
    )
