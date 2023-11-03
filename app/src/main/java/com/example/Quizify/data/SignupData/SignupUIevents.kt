package com.example.Quizify.data.SignupData

sealed class SignupUIevents{
    data class NameChanged(val name:String): SignupUIevents()
    data class EmailChanged(val email:String): SignupUIevents()
    data class PhoneChanged(val phone: String): SignupUIevents()
    data class PasswordChanged(val password:String): SignupUIevents()
    data class PrivacyCheckBoxChanged(val isChecked:Boolean): SignupUIevents()

    object signupButtonClicked: SignupUIevents()
}
