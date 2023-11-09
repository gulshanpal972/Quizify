package com.example.Quizify.data.ProfileData

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel: ViewModel() {
    private val TAG= ProfileViewModel::class.simpleName
    var profileuistate= mutableStateOf(Profileuistate())

    var allValidationPassed= mutableStateOf(false)

    var profileinprogress= mutableStateOf(false)

    fun onEvent(event: ProfileUIevents){
        when(event){
            is ProfileUIevents.NameChanged ->{
                profileuistate.value=profileuistate.value.copy(
                    name = event.name
                )
                printState()
            }
            is ProfileUIevents.EmailChanged ->{
                profileuistate.value=profileuistate.value.copy(
                    email = event.email
                )
                printState()
            }
            is ProfileUIevents.PhoneChanged ->{
                profileuistate.value=profileuistate.value.copy(
                    phone = event.phone
                )
                printState()
            }
            is ProfileUIevents.DOBChanged ->{
                profileuistate.value=profileuistate.value.copy(
                    dob = event.dob
                )
                printState()
            }

            is ProfileUIevents.profileButtonClicked ->{
                profile()
            }
        }
    }

    private fun profile() {
        Log.d(TAG,"Inside_printState")
        printState()
        createUserFirebase(
            email=profileuistate.value.email,
            password=profileuistate.value.dob
        )
    }

    private fun printState(){
        Log.d(TAG,"Inside_printState")
        Log.d(TAG,profileuistate.value.toString())
    }

    private fun createUserFirebase(email:String,password:String){
        profileinprogress.value=true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG,"isSuccessful= ${it.isSuccessful}")
                profileinprogress.value=false
                if(it.isSuccessful){
                    Quizapprouter.navigateTo(Screen.Homeactivity)
                }
            }
            .addOnFailureListener{
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }
    }
}