package com.example.Quizify.data.ProfileData

sealed class ProfileUIevents{
    data class NameChanged(val name:String): ProfileUIevents()
    data class EmailChanged(val email:String): ProfileUIevents()
    data class PhoneChanged(val phone: String): ProfileUIevents()
    data class DOBChanged(val dob:String): ProfileUIevents()

    object profileButtonClicked: ProfileUIevents()
}
