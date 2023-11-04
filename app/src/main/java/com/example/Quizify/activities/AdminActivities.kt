package com.example.Quizify.activities

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.Quizify.components.AdminFullTestComponent
import com.example.Quizify.components.CardComponentHalfWidth
import com.example.Quizify.data.NavigationDrawer.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminActivity(homeViewModel: HomeViewModel = viewModel()){
    homeViewModel.checkActiveSession()
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
