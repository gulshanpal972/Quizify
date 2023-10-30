package com.example.Quizify.data.loginData

sealed class LoginUIevents{
    data class EmailChanged(var email:String): LoginUIevents()
    data class PasswordChanged(var password:String): LoginUIevents()

    object loginButtonClicked: LoginUIevents()
    object adminLoginButtonClicked: LoginUIevents()
}
