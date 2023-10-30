package com.example.Quizify.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Quizify.components.AdminCardComponentMarathon
import com.example.Quizify.components.AdminFullTestComponent
import com.example.Quizify.components.CardComponentFullWidth
import com.example.Quizify.components.CardComponentHalfWidth

@Composable
fun AdminActivity(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AdminCardComponentMarathon(cardHeight = 120)
        AdminFullTestComponent(cardHeight = 120)
        Spacer(modifier = Modifier.height(4.dp))
        Text(modifier=Modifier.fillMaxWidth(),
            text = "Topic Wise Quizzes",
            fontSize =  20.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row{
            CardComponentHalfWidth(subjectName="Mathematics")
            CardComponentHalfWidth(subjectName="Reasoning")
        }
        Row{
            CardComponentHalfWidth(subjectName="English")
            CardComponentHalfWidth(subjectName="Pseudo Code")
        }
        Row{
            CardComponentHalfWidth(subjectName="English")
            CardComponentHalfWidth(subjectName="Pseudo Code")
        }
    }
}

@Preview
@Composable
fun PreviewAdmin(){
    AdminActivity()
}
