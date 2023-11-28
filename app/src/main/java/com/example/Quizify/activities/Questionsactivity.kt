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
import androidx.compose.runtime.DisposableEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.navigation.SystemBackButtonHandler
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

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

    if (questions.isNotEmpty() && currentIndex < questions.size) {
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
}

@Composable
fun Questionsactivity() {
    println("subjectName: ${Quizapprouter.currentSubjectName}")
    println("setName: ${Quizapprouter.currentSetName}")

    var subjectName = Quizapprouter.currentSubjectName
    var setName = Quizapprouter.currentSetName

    // Check if subjectName and setName are not null
    if (subjectName != null && setName != null) {
        val databaseReference = Firebase.database.getReference(subjectName).child(setName)

        var loading by remember { mutableStateOf(true) }
        var questions by remember { mutableStateOf<List<Question>>(emptyList()) }

        DisposableEffect(databaseReference) {
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val newQuestions = mutableListOf<Question>()

                    for (questionSnapshot in snapshot.children) {
                        val questionText =
                            questionSnapshot.child("Question").getValue(String::class.java) ?: ""
                        val options = listOf(
                            questionSnapshot.child("Option1").getValue(String::class.java) ?: "",
                            questionSnapshot.child("Option2").getValue(String::class.java) ?: "",
                            questionSnapshot.child("Option3").getValue(String::class.java) ?: "",
                            questionSnapshot.child("Option4").getValue(String::class.java) ?: ""
                        )

                        val question = Question(questionText, options)
                        newQuestions.add(question)
                    }

                    questions = newQuestions
                    loading = false
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                    loading = false
                }
            }

            // Attach the listener
            databaseReference.addListenerForSingleValueEvent(valueEventListener)

            // Remove the listener when the composable is disposed
            onDispose {
                databaseReference.removeEventListener(valueEventListener)
            }
        }

        if (loading) {
            // Show loading indicator if needed
        } else {
            ListWithButtons(questions = questions)
        }
    }

    SystemBackButtonHandler()
}
