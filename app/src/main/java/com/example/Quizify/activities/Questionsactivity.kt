package com.example.Quizify.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.navigation.SystemBackButtonHandler

data class Question(
    val questionText: String,
    val options: List<String>
)

@Composable
fun QuestionItem(question: Question, questionNumber: Int, selectedAnswer: MutableState<String?>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Question $questionNumber:",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = question.questionText,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        val radioOptions = rememberUpdatedState(question.options)
        val selectedOption = selectedAnswer.value
        radioOptions.value.forEach { option ->
            val isSelected = option == selectedOption
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            if (!isSelected) {
                                selectedAnswer.value = option
                            }
                        }
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            selectedAnswer.value = option
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }
    }
}

@Composable
fun ListWithButtons(questions: List<Question>) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val selectedAnswer = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        QuestionItem(
            question = questions[currentIndex],
            questionNumber = currentIndex + 1,
            selectedAnswer = selectedAnswer
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) {
                        currentIndex--
                        selectedAnswer.value = null // Reset selected answer
                    }
                },
                enabled = currentIndex > 0
            ) {
                Text(text = "Back")
            }

            Button(
                onClick = {
                    if (currentIndex < questions.size - 1) {
                        currentIndex++
                        selectedAnswer.value = null // Reset selected answer
                    }
                },
                enabled = currentIndex < questions.size - 1
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun Questionsactivity() {
    val questions = listOf(
        Question(
            "What is the capital of Bharat?",
            listOf("New Delhi", "Mumbai", "Kolkata", "Bangluru")
        ),
        Question(
            "What is the largest planet in our solar system?",
            listOf("Earth", "Mars", "Jupiter", "Saturn")
        ),
        Question(
            "Who is the president of Bharat?",
            listOf("Ram Nath Kovind", "Draupadi Murmu", "Narendra Modi", "Subhramanyam Jaishankar")
        )
    )

    ListWithButtons(questions = questions)

    SystemBackButtonHandler {
        Quizapprouter.navigateTo(Screen.PracticeSets)
    }
}