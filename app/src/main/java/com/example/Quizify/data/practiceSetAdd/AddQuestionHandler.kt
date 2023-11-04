package com.example.Quizify.data.practiceSetAdd

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddQuestionHandler : ViewModel() {
    private val TAG = AddQuestionHandler::class.simpleName
    var questionuistate = mutableStateOf(QuestionUIState())

    fun onEvent(event: QuestionUIEvents) {
        when (event) {
            is QuestionUIEvents.NumberOfQuestions -> {
                questionuistate.value = questionuistate.value.copy(
                    numberOfQuestions = event.numberOfQuestions
                )
                printState()
            }

            is QuestionUIEvents.Duration -> {
                questionuistate.value = questionuistate.value.copy(
                    duration = event.duration
                )
                printState()
            }

            is QuestionUIEvents.Question -> {
                questionuistate.value = questionuistate.value.copy(
                    question = event.question
                )
                printState()
            }

            is QuestionUIEvents.Option1 -> {
                questionuistate.value = questionuistate.value.copy(
                    option1 = event.option1
                )
                printState()
            }
            is QuestionUIEvents.Option2 -> {
                questionuistate.value = questionuistate.value.copy(
                    option2 = event.option2
                )
                printState()
            }
            is QuestionUIEvents.Option3 -> {
                questionuistate.value = questionuistate.value.copy(
                    option3 = event.option3
                )
                printState()
            }
            is QuestionUIEvents.Option4 -> {
                questionuistate.value = questionuistate.value.copy(
                    option4 = event.option4
                )
                printState()
            }
            is QuestionUIEvents.Answer -> {
                questionuistate.value = questionuistate.value.copy(
                    answer = event.answer
                )
                printState()
            }

            is QuestionUIEvents.Upload ->{

            }
        }
    }
    private fun printState(){
        Log.d(TAG,"Inside_printState")
        Log.d(TAG,questionuistate.value.toString())
    }
}