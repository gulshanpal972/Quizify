package com.example.Quizify.data.SignupData

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupViewModel : ViewModel() {
    private val TAG = SignupViewModel::class.simpleName
    val signupuistate = mutableStateOf(Signupuistate())
    val isPrivacyCheckBoxChecked = mutableStateOf(false)
    val signupinprogress = mutableStateOf(false)
    var emailError= mutableStateOf(false)

    fun onEvent(event: SignupUIevents) {
        when (event) {
            is SignupUIevents.NameChanged -> {
                signupuistate.value = signupuistate.value.copy(
                    name = event.name
                )
                printState()
            }
            is SignupUIevents.EmailChanged -> {
                signupuistate.value = signupuistate.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignupUIevents.PhoneChanged -> {
                signupuistate.value = signupuistate.value.copy(
                    phone = event.phone
                )
                printState()
            }
            is SignupUIevents.PasswordChanged -> {
                signupuistate.value = signupuistate.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignupUIevents.PrivacyCheckBoxChanged -> {
                isPrivacyCheckBoxChecked.value = event.isChecked
            }

            is SignupUIevents.signupButtonClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        Log.d(TAG, "Inside_printState")
        printState()
        createUserFirebase(
            name=signupuistate.value.name,
            email = signupuistate.value.email,
            phone=signupuistate.value.phone,
            password = signupuistate.value.password
        )
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, signupuistate.value.toString())
    }

    private fun createUserFirebase(name: String, email: String, phone: String, password: String) {
        Log.d(TAG, "Inside_createUserFirebase")
        signupinprogress.value = true
        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
            .addOnCompleteListener{task->
                if (task.isSuccessful) {
                    val methods = task.result?.signInMethods
                    if (methods.isNullOrEmpty()) {
                        FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { createTask ->
                                Log.d(TAG, "Inside_OnCompleteListener")
                                if (createTask.isSuccessful) {
                                    Log.d(TAG, "User registration successful.")

                                    val userID = FirebaseAuth.getInstance().currentUser?.uid

                                    if (userID != null) {
                                        val documentReference = FirebaseFirestore.getInstance().collection("users").document(userID)
                                        val user = HashMap<String, Any>()
                                        user["Name"] = name
                                        user["Email"] = email
                                        user["Phone"] = phone

                                        documentReference
                                            .set(user)
                                            .addOnSuccessListener { void ->
                                                Log.d(TAG, "Data inserted successfully")
                                                signupinprogress.value = false
                                                Quizapprouter.navigateTo(Screen.Homeactivity)
                                            }
                                            .addOnFailureListener { e ->
                                                Log.e(TAG, "Error inserting data: ${e.message}")
                                                signupinprogress.value = false
                                            }
                                    }
                                } else {
                                    Log.d(TAG, "User registration failed.")
                                    signupinprogress.value = false
                                    Log.e(TAG, "Error message: ${task.exception?.message}")
                                }
                            }
                            .addOnFailureListener { e ->
                                Log.d(TAG, "Inside_OnFailureListener")
                                signupinprogress.value = false
                                emailError.value = true
                                Log.e(TAG, "Exception: ${e.message}")
                                Log.e(TAG, "Localized Message: ${e.localizedMessage}")
                                e.printStackTrace()
                            }
                    }else {
                        // Email is already registered
                        emailError.value = true
                        signupinprogress.value = false
                        Log.d(TAG,"hhhhhhhhhhhhhhhhh")
                    }
                } else {
                    // Error occurred while checking email
                    Log.d(TAG,"Error occured while checking email in database")
                }

            }
    }
}

