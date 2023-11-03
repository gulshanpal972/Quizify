package com.example.Quizify.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Quizify.components.ButtonComponent
import com.example.Quizify.components.HeadingTextComponent
import com.example.Quizify.components.NormalTextComponent
import com.example.Quizify.components.PasswordTextFields
import com.example.Quizify.components.TextFields
import com.example.Quizify.data.loginData.LoginUIevents
import com.example.Quizify.data.loginData.LoginViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.navigation.SystemBackButtonHandler
import com.example.Quizify.R

@Composable
fun AdminLoginActivity(loginViewModel: LoginViewModel = viewModel()){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Surface(
            color= Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponent(value = "Admin Sir")
                HeadingTextComponent(value = stringResource(id = R.string.welcomeback))
                Spacer(modifier = Modifier.height(20.dp))
                TextFields(labelValue = "Email", painterResource(R.drawable.email), onTextSelected = {
                    loginViewModel.onEvent(LoginUIevents.EmailChanged(it))
                })
                PasswordTextFields(labelValue = "Password", painterResource(R.drawable.password)) {
                    loginViewModel.onEvent(LoginUIevents.PasswordChanged(it))
                }
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(value = "Login", onButtonClicked = {
                    loginViewModel.onEvent(LoginUIevents.adminLoginButtonClicked)
                })
            }
        }

        if(loginViewModel.loginInProgress.value){
            CircularProgressIndicator()
        }
    }

    SystemBackButtonHandler {
        Quizapprouter.navigateTo(Screen.Loginactivity)
    }
}

@Preview
@Composable
fun AdminLoginpreview(){
    AdminLoginActivity()
}