package com.example.Quizify.activities

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.navigation.SystemBackButtonHandler

@Composable
fun PracticeSets(selectedSubject: String?){
    Log.d("Practicesets","SubjectName: $selectedSubject")
    var count by remember { mutableStateOf(0L) }
    LaunchedEffect(selectedSubject) {
        count = getTotalSets(selectedSubject)
    }
    val names:List<String> =List(count.toInt()){(it + 1).toString()}
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(15.dp)) {
        Column {

            Spacer(modifier = Modifier.height(8.dp))
            Text(modifier=Modifier.fillMaxWidth(),
                text = "Continue Pratice",
                fontSize =  20.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            LazyColumn(modifier = Modifier
                .padding(vertical = 4.dp)
                .weight(1f)){
                items(items=names){name->
                    if (selectedSubject != null) {
                        ListItem(name = name, subjectName=selectedSubject)
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(name: String, subjectName:String){
    var setName: String
    Surface(
        color = Color(0xFFD7EFFE),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        onClick={println("Clicked on Practice Set $name")
            setName="set$name"
            Quizapprouter.navigateTo(Screen.Questionsactivity, subjectName, setName)}
    ) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Practice Set $name", style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ), color = Color(0xFF073042)
                )
            }
        }
    }
    SystemBackButtonHandler()
}