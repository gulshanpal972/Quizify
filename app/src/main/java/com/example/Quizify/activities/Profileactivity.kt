package com.example.Quizify.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Quizify.R
import com.example.Quizify.components.ButtonComponent
import com.example.Quizify.components.DatePickerTextField
import com.example.Quizify.components.ProfileImage
import com.example.Quizify.components.TextFields
import com.example.Quizify.data.ProfileData.ProfileUIevents
import com.example.Quizify.data.ProfileData.ProfileViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.navigation.SystemBackButtonHandler
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Profileactivity(profileViewModel: ProfileViewModel = viewModel()){
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 30.dp, vertical = 20.dp)) {
        
        Column{
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                ProfileImage()
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Edit", style = TextStyle(
                    fontSize = 18.sp
                ),)
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextFields(labelValue = "Name: ", painterResource(R.drawable.profile),onTextSelected = {
                profileViewModel.onEvent(ProfileUIevents.NameChanged(it))
            })
            TextFields(labelValue = "Email: ", painterResource(R.drawable.email),onTextSelected = {
                profileViewModel.onEvent(ProfileUIevents.EmailChanged(it))
            })
            TextFields(labelValue = "Phone: ", painterResource(R.drawable.phone),onTextSelected = {
                profileViewModel.onEvent(ProfileUIevents.PhoneChanged(it))
            })
            DatePickerTextField(label = "DOB: ",selectedDate = selectedDate) { newDate ->
                selectedDate = newDate }
            Spacer(modifier = Modifier.height(30.dp))
            ButtonComponent(value = "Save Profile", onButtonClicked = { /*TODO*/ })
        }
    }
    SystemBackButtonHandler {
        Quizapprouter.navigateTo(Screen.Homeactivity)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewProfile(){
    Profileactivity()
}