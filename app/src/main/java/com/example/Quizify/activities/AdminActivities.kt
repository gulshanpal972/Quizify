package com.example.Quizify.activities

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Quizify.R
import com.example.Quizify.components.AdminCardComponentMarathon
import com.example.Quizify.components.CardComponentFullWidth
import com.example.Quizify.data.NavigationDrawer.HomeViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminActivity(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                modifier = Modifier.background(color = colorResource(id = R.color.appcolor)),
                title = {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment= Alignment.Center
                    ){
                        Text(text = "Quizify", color = Color.White,modifier = Modifier.graphicsLayer {
                            translationX = (-16).dp.toPx()
                        })
                    }},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent )
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row {
                CardComponentFullWidth(cardHeight = 120, subjectName="FullTest"){subjectName->
                    Quizapprouter.navigateTo(Screen.AddSet(subjectName))
                }
            }

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
                CardComponentSubjectWise(subjectName="Mathematics"){subjectName ->
                    Quizapprouter.navigateTo(Screen.AddSet(subjectName))
                }
                CardComponentSubjectWise(subjectName="Reasoning"){subjectName ->
                    Quizapprouter.navigateTo(Screen.AddSet(subjectName))
                }
            }
            Row{
                CardComponentSubjectWise(subjectName="English"){subjectName ->
                    Quizapprouter.navigateTo(Screen.AddSet(subjectName))
                }
                CardComponentSubjectWise(subjectName="Pseudo Code"){subjectName ->
                    Quizapprouter.navigateTo(Screen.AddSet(subjectName))
                }
            }
            Row{
                CardComponentSubjectWise(subjectName="General Knowledge"){subjectName ->
                    Quizapprouter.navigateTo(Screen.AddSet(subjectName))
                }
                CardComponentSubjectWise(subjectName="History"){subjectName ->
                    Quizapprouter.navigateTo(Screen.AddSet(subjectName))
                }
            }
        }
}

@Composable
fun CardComponentSubjectWise(subjectName:String?, onClick: (String) -> Unit) {
    if (subjectName != null) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFFDAE1E7),
            modifier = Modifier
                .height(150.dp)
                .padding(10.dp)
                .width(160.dp),
            onClick = { onClick(subjectName) },
            shadowElevation = 10.dp
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(modifier=Modifier.fillMaxWidth(),
                        text = subjectName,
                        fontSize =  18.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    } else {
        Log.e("CardComponentHalfWidth", "SubjectName is null")
    }
}