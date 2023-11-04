package com.example.Quizify.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Quizify.components.ButtonComponent
import com.example.Quizify.data.loginData.LoginUIevents
import com.example.Quizify.data.practiceSetAdd.AddQuestionHandler
import com.example.Quizify.data.practiceSetAdd.QuestionUIEvents

@Composable
fun AddQuestions(questionViewModel: AddQuestionHandler = viewModel()){
    val state = questionViewModel.questionuistate.value
    var numberOfQuestions by remember { mutableStateOf(0) }
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(10.dp)){

        Column(modifier=Modifier.fillMaxSize()) {
            Row(modifier=Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Number of Questions: ", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                TwoLineTextFieldDemo(
                    typevalue = KeyboardType.Number,
                    onValueChanged = { numberOfQuestions = it.toIntOrNull() ?: 0 }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier=Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Duration (In minutes): ", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                TwoLineTextFieldDemo(typevalue = KeyboardType.Text, onValueChanged = {})
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (numberOfQuestions!=0){
                Row(modifier=Modifier.fillMaxWidth()){
                    Text(modifier = Modifier.fillMaxWidth(), text = "Add Questions", textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(numberOfQuestions) { index ->
                    AddQuestionCard(index + 1)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            if (numberOfQuestions!=0){
                ButtonComponent(value = "Upload", onButtonClicked = {
                    questionViewModel.onEvent(QuestionUIEvents.Upload)
                })
            }
        }
    }
}


@Composable
fun AddQuestionCard(questionNumber: Int){
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(4.dp)){
                Text(
                    text = "Question $questionNumber: ",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                TwoLineTextFieldDemo(typevalue = KeyboardType.Text, onValueChanged = {})
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Options 1: ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo(typevalue = KeyboardType.Text, onValueChanged = {})
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Options 2: ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo(typevalue = KeyboardType.Text, onValueChanged = {})
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Options 3: ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo(typevalue = KeyboardType.Text, onValueChanged = {})
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Options 4: ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo(typevalue = KeyboardType.Text, onValueChanged = {})
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Answer:     ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo(typevalue = KeyboardType.Text, onValueChanged = {})
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@Composable
fun TwoLineTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    keyboardTypevalue: KeyboardType
) {
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardTypevalue,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                isFocused = false
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isFocused) Color.Blue else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            }
    )
}

@Composable
fun TwoLineTextFieldDemo(typevalue:KeyboardType, onValueChanged: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    TwoLineTextField(
        text = text,
        onTextChanged = {
            text = it
            onValueChanged(it)
        },
        keyboardTypevalue = typevalue
    )
}