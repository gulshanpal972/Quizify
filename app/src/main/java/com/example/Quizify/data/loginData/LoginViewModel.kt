package com.example.Quizify.data.loginData

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.Quizify.data.validationrules.Validator
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {
    private val TAG= LoginViewModel::class.simpleName
    var loginuistate= mutableStateOf(Loginuistate())

    var allValidationPassed= mutableStateOf(false)
    var loginInProgress= mutableStateOf(false)

    fun onEvent(event: LoginUIevents){
        validateData()
        when(event){
            is LoginUIevents.EmailChanged ->{
                loginuistate.value=loginuistate.value.copy(
                    email = event.email
                )
                printState()
            }
            is LoginUIevents.PasswordChanged ->{
                loginuistate.value=loginuistate.value.copy(
                    password = event.password
                )
                printState()
            }
            is LoginUIevents.loginButtonClicked ->{
                login()
            }
            is LoginUIevents.adminLoginButtonClicked->{
                adminLogin()
            }
        }
    }

    private fun adminLogin() {
        TODO("Not yet implemented")
    }

    private fun login() {
        loginInProgress.value=true
        Log.d(TAG,"Inside_printState")
        printState()
        loginUserFirebase(email=loginuistate.value.email, password=loginuistate.value.password)
    }

    private fun loginUserFirebase(email: String, password: String) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                Log.d(TAG, "login successful")
                loginInProgress.value=false
                if(it.isSuccessful){
                    Quizapprouter.navigateTo(Screen.Homeactivity)
                }
            }
            .addOnFailureListener{
                Log.d(TAG, "login failed")
                Log.d(TAG, it.localizedMessage)
                loginInProgress.value=false
            }
    }

    private fun validateData() {
        val emailValidator= Validator.validateEmail(
            email=loginuistate.value.email
        )
        val passwordValidator= Validator.validatePassword(
            password = loginuistate.value.password
        )

        Log.d(TAG, "Inside_validationData")
        Log.d(TAG, "email = $emailValidator")
        Log.d(TAG, "password = $passwordValidator")

        loginuistate.value=loginuistate.value.copy(
            emailerror = emailValidator.status,
            passworderror = passwordValidator.status
        )

        allValidationPassed.value=emailValidator.status && passwordValidator.status
    }

    private fun printState(){
        Log.d(TAG,"Inside_printState")
        Log.d(TAG,loginuistate.value.toString())
    }
}