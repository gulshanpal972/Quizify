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
import com.example.Quizify.components.CheckBoxComponent
import com.example.Quizify.components.ClickableTextComponent
import com.example.Quizify.components.DividerTextComponent
import com.example.Quizify.components.HeadingTextComponent
import com.example.Quizify.components.NormalTextComponent
import com.example.Quizify.components.PasswordTextFields
import com.example.Quizify.components.TextFields
import com.example.Quizify.data.SignupData.SignupViewModel
import com.example.Quizify.data.SignupData.SignupUIevents
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.R

@Composable
fun Signupactivity(signupViewModel: SignupViewModel = viewModel()){
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
                NormalTextComponent(value = stringResource(id = R.string.heythere))
                HeadingTextComponent(value = stringResource(id = R.string.createaccount))
                Spacer(modifier = Modifier.height(20.dp))
                TextFields(labelValue = "Full Name", painterResource(id = R.drawable.profile), onTextSelected = {
                    signupViewModel.onEvent(SignupUIevents.NameChanged(it))
                }, errorStatus = signupViewModel.signupuistate.value.nameerror)
                TextFields(labelValue = "Email", painterResource(id = R.drawable.email), onTextSelected = {
                    signupViewModel.onEvent(SignupUIevents.EmailChanged(it))
                }, errorStatus = signupViewModel.signupuistate.value.emailerror)
                TextFields(labelValue = "Contact", painterResource(id = R.drawable.phone), onTextSelected = {
                    signupViewModel.onEvent(SignupUIevents.PhoneChanged(it))
                }, errorStatus = signupViewModel.signupuistate.value.phoneerror)
                PasswordTextFields(labelValue = "Password", painterResource(id = R.drawable.password), onTextSelected = {
                    signupViewModel.onEvent(SignupUIevents.PasswordChanged(it))
                }, errorStatus = signupViewModel.signupuistate.value.passworderror)
                CheckBoxComponent(value = "", onTextSelected = {
                    Quizapprouter.navigateTo(Screen.Termsandcondition)
                }, onCheckedChange = {
                    signupViewModel.onEvent(SignupUIevents.PrivacyCheckBoxChanged(it))
                })

                Spacer(modifier = Modifier.height(60.dp))
                ButtonComponent(value = "Register", onButtonClicked = {
                    signupViewModel.onEvent(SignupUIevents.signupButtonClicked)
                }, isEnabled = signupViewModel.allValidationPassed.value)
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()

                ClickableTextComponent(value1 = "Already have an account? ", value2 = "Login Here", onTextSelected = {
                    Quizapprouter.navigateTo(Screen.Loginactivity)
                })
            }
        }

        if(signupViewModel.signupinprogress.value){
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun DefaultPreviewSignupactivity(){
    Signupactivity()
}