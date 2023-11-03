package com.example.Quizify.data.loginData

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    var loginuistate = mutableStateOf(Loginuistate())
    var loginInProgress = mutableStateOf(false)
    var loginError = mutableStateOf<String?>(null) // Track login error message

    fun onEvent(event: LoginUIevents) {
        when (event) {
            is LoginUIevents.EmailChanged -> {
                loginuistate.value = loginuistate.value.copy(
                    email = event.email
                )
                printState()
            }
            is LoginUIevents.PasswordChanged -> {
                loginuistate.value = loginuistate.value.copy(
                    password = event.password
                )
                printState()
            }
            is LoginUIevents.loginButtonClicked -> {
                login()
            }
            is LoginUIevents.adminLoginButtonClicked -> {
                adminLogin()
            }
        }
    }

    private fun adminLogin() {
        // TODO: Implement admin login
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
            .addOnCompleteListener { task ->
                loginInProgress.value = false
                if (task.isSuccessful) {
                    Quizapprouter.navigateTo(Screen.Homeactivity)
                } else {
                    val errorMessage = task.exception?.message
                    loginError.value = errorMessage ?: "Login failed"
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "login failed")
                Log.d(TAG, it.localizedMessage)
                loginInProgress.value = false
            }
    }



    private fun printState(){
        Log.d(TAG,"Inside_printState")
        Log.d(TAG,loginuistate.value.toString())
    }
}