package com.example.Quizify.data.SignupData

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.Quizify.data.validationrules.Validator
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class SignupViewModel: ViewModel() {
    private val TAG= SignupViewModel::class.simpleName
    var signupuistate= mutableStateOf(Signupuistate())

    var allValidationPassed= mutableStateOf(false)

    var signupinprogress= mutableStateOf(false)

    fun onEvent(event: SignupUIevents){
        validateData()
        when(event){
            is SignupUIevents.NameChanged ->{
                signupuistate.value=signupuistate.value.copy(
                    name = event.name
                )
                printState()
            }
            is SignupUIevents.EmailChanged ->{
                signupuistate.value=signupuistate.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignupUIevents.PhoneChanged ->{
                signupuistate.value=signupuistate.value.copy(
                    phone = event.phone
                )
                printState()
            }
            is SignupUIevents.PasswordChanged ->{
                signupuistate.value=signupuistate.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignupUIevents.PrivacyCheckBoxChanged ->{
                signupuistate.value=signupuistate.value.copy(
                    privacypolicyaccepted = event.status
                )
            }

            is SignupUIevents.signupButtonClicked ->{
                signUp()
            }
        }
    }

    private fun signUp() {
        Log.d(TAG,"Inside_printState")
        printState()
        createUserFirebase(
            email=signupuistate.value.email,
            password=signupuistate.value.password
        )
    }

    private fun validateData() {
        val nameValidator=Validator.validateName(
            name=signupuistate.value.name
        )

        val emailValidator=Validator.validateEmail(
            email=signupuistate.value.email
        )

        val phoneValidator=Validator.validatePhone(
            phone = signupuistate.value.phone
        )

        val passwordValidator=Validator.validatePassword(
            password = signupuistate.value.password
        )

        val privacyPolicyValidator=Validator.privacyPolicyAcceptance(
            statusvalue = signupuistate.value.privacypolicyaccepted
        )

        Log.d(TAG, "Inside_validationData")
        Log.d(TAG, "name = $nameValidator")
        Log.d(TAG, "email = $emailValidator")
        Log.d(TAG, "phone = $phoneValidator")
        Log.d(TAG, "password = $passwordValidator")
        Log.d(TAG, "privacypolicy= $privacyPolicyValidator")

        signupuistate.value=signupuistate.value.copy(
            nameerror = nameValidator.status,
            emailerror = emailValidator.status,
            phoneerror = phoneValidator.status,
            passworderror = passwordValidator.status,
            privacycheckboxerror = privacyPolicyValidator.status
        )
        allValidationPassed.value = nameValidator.status && emailValidator.status && phoneValidator.status && passwordValidator.status && privacyPolicyValidator.status
    }

    private fun printState(){
        Log.d(TAG,"Inside_printState")
        Log.d(TAG,signupuistate.value.toString())
    }

    private fun createUserFirebase(email:String,password:String){
        signupinprogress.value=true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG,"isSuccessful= ${it.isSuccessful}")
                signupinprogress.value=false
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