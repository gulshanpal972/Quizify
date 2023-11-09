package com.example.Quizify.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.navigation.SystemBackButtonHandler
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

@Composable
fun FullTest() {
    // Initialize Firebase database reference
    val database: DatabaseReference = Firebase.database.reference

    // Initialize shuffledQuestions and userAnswers using remember
    var shuffledQuestions by remember { mutableStateOf<List<Question>>(emptyList()) }
    var userAnswers by remember { mutableStateOf(mutableListOf<String?>()) }

    // Other variables
    var currentIndex by remember { mutableStateOf(0) }
    val selectedAnswer = remember { mutableStateOf<String?>(null) }
    var isQuizCompleted by remember { mutableStateOf(false) }

    // Count of correct answers
    var correctAnswersCount by remember { mutableStateOf(0) }

    // Timer for the quiz (initial time in seconds)
    var timer by remember { mutableStateOf(1500) }

    // Callback to update correctAnswersCount
    val onAnswerSelected: (Boolean) -> Unit = { isCorrect ->
        if (isCorrect) {
            correctAnswersCount++
        }
    }

    // Fetch the questions and shuffle them
    LaunchedEffect(Unit) {
        database.child("FullTest").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val fetchedQuestions = mutableListOf<Question>()
                for (childSnapshot in snapshot.children) {
                    val question = childSnapshot.child("question").getValue(String::class.java) ?: ""
                    val answers = childSnapshot.child("options")
                        .getValue(object : GenericTypeIndicator<List<String>>() {}) ?: emptyList()
                    val correctAnswer = childSnapshot.child("correct_answer")
                        .getValue(String::class.java) ?: ""
                    val questionItem = Question(question, answers, correctAnswer)
                    fetchedQuestions.add(questionItem)
                }
                // Update shuffledQuestions and userAnswers
                shuffledQuestions = fetchedQuestions.shuffled().take(25)
                userAnswers = MutableList(shuffledQuestions.size) { null }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        // Start the timer countdown
        while (timer > 0 && !isQuizCompleted) {
            delay(1000)
            timer--
        }

        // Automatically submit the quiz when the timer is up
        if (timer == 0 && !isQuizCompleted) {
            isQuizCompleted = true
            // No need to count here; it's already done in onAnswerSelected
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display the timer
        Timer(timer)

        if (shuffledQuestions.isNotEmpty() && currentIndex < 25) {
            QuestionItem(
                question = shuffledQuestions[currentIndex],
                questionNumber = currentIndex + 1,
                selectedAnswer = selectedAnswer,
                onAnswerSelected = onAnswerSelected
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isQuizCompleted) {
            Text(
                text = "Quiz Completed! You got $correctAnswersCount out of 25 questions correct.",
                fontSize = 20.sp
            )
            Button(onClick = {
                // Handle navigation to Main Menu
                Quizapprouter.navigateTo(Screen.Homeactivity)
            }) {
                Text(text = "Main Menu")
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (currentIndex > 0) {
                            currentIndex--
                            selectedAnswer.value = userAnswers[currentIndex]
                        }
                    },
                    enabled = currentIndex > 0
                ) {
                    Text(text = "Back")
                }

                // Check if the user has answered all 25 questions
                if (currentIndex == 24) {
                    Button(
                        onClick = {
                            isQuizCompleted = true
                            // No need to count here; it's already done in onAnswerSelected
                        }
                    ) {
                        Text(text = "Submit")
                    }
                } else {
                    Button(
                        onClick = {
                            if (currentIndex < 24) {
                                currentIndex++
                                selectedAnswer.value = null
                            }
                        },
                        enabled = currentIndex < 24
                    ) {
                        Text(text = "Next")
                    }
                }
            }
        }
    }
    SystemBackButtonHandler()
}

@Composable
fun Timer(timer: Int) {
    val minutes = timer / 60
    val seconds = timer % 60
    Text(text = "Time Left: $minutes min $seconds sec", fontSize = 16.sp)
}
