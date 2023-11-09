package com.example.Quizify.data.NavigationDrawer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Quizify.data.SignupData.SignupViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel:ViewModel() {
    private val TAG= SignupViewModel::class.simpleName

    val isUserLoggedIn : MutableLiveData<Boolean> = MutableLiveData()

    val uName:MutableLiveData<String> = MutableLiveData()

    fun logout(){
        Quizapprouter.clearScreenStack()
        val firebaseAuth= FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener= FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Sign out success")
                Quizapprouter.navigateTo(Screen.Loginactivity)
            } else {
                Log.d(TAG, "Sign out not success")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun checkActiveSession(){
        if(FirebaseAuth.getInstance().currentUser!=null){
            Log.d(TAG, "Valid Session")
            isUserLoggedIn.value=true
        }else{
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value=false
        }
    }

    fun getUserData() {
        val userID = FirebaseAuth.getInstance().currentUser?.uid

        userID?.let { FirebaseFirestore.getInstance().collection("users").document(it) }
            ?.addSnapshotListener(EventListener<DocumentSnapshot> { documentSnapshot, e ->
                if (e != null) {
                    Log.d(TAG, "Error in fetching data in HomeViewModel getUserData()")
                } else {
                    uName.value = documentSnapshot?.getString("Name")
                    Log.d(TAG,"failed name ${uName.value}")
                }
            })
    }
}