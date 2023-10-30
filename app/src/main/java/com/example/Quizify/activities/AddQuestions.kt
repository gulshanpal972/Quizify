package com.example.Quizify.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Quizify.components.AddQuestionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuestions(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(10.dp)){

        Column(modifier=Modifier.fillMaxSize()) {
            Row(modifier=Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Number of Questions: ", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = "", onValueChange = {})
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier=Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Duration (In minutes): ", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = "", onValueChange = {})
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier=Modifier.fillMaxWidth()){
                Text(modifier = Modifier.fillMaxWidth(), text = "Add Questions", textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))

            AddQuestionCard()
        }
    }
}
