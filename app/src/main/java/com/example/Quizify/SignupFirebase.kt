package com.example.Quizify

import android.app.Application
import com.google.firebase.FirebaseApp

class SignupFirebase:Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}