package com.example.Quizify.data.practiceSetAdd

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.Quizify.activities.AddSetHandler
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class AddQuestionHandler : ViewModel() {
    private val TAG = AddQuestionHandler::class.simpleName
    var questionuistate = mutableStateOf(QuestionUIState())
    var questionnumber = mutableStateOf(0)

    fun onEvent(event: QuestionUIEvents, addSetHandler: AddSetHandler) {
        when (event) {

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

            is QuestionUIEvents.Upload -> {
                // Accessing subjectName from AddSetHandler
                val subjectName = addSetHandler.setuistate.value.subjectName
                Log.d(TAG, "SubjectName: $subjectName")

                // Upload the questions to the database
                if (subjectName != null) {
                    questionnumber.value = questionnumber.value + 1
                    uploadQuestions(subjectName)
                } else {
                    Log.e(TAG, "SubjectName is null when trying to upload questions")
                    // Handle the case when subjectName is null
                }
            }
        }
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, questionuistate.value.toString())
    }

    private var setNumberKey: String? = null

    private fun uploadQuestions(subjectName: String) {
        // Check if the set has been created
        if (setNumberKey == null) {
            // If not, create the set
            val count = runBlocking {
                getTotalSets(subjectName)
            }

            val database = Firebase.database
            val databasename = subjectName
            val mydb = database.getReference(databasename)

            val nextSetNumber = count + 1
            setNumberKey = "set$nextSetNumber"

            mydb.child(setNumberKey!!).apply {
                // You can perform any setup for the new set here if needed
                // ...
            }
        }

        // Continue with uploading questions
        val database = Firebase.database
        val databasename = subjectName
        val mydb = database.getReference(databasename)

        val uploaddata = mydb.child(setNumberKey!!).child("Question${questionnumber.value}")

        uploaddata.child("Question").setValue(questionuistate.value.question)
        uploaddata.child("Option1").setValue(questionuistate.value.option1)
        uploaddata.child("Option2").setValue(questionuistate.value.option2)
        uploaddata.child("Option3").setValue(questionuistate.value.option3)
        uploaddata.child("Option4").setValue(questionuistate.value.option4)
        uploaddata.child("Answer").setValue(questionuistate.value.answer)

        Log.d(TAG, "Questions uploaded successfully for subject: $subjectName")
        if((questionnumber.value==10 && subjectName!="FullTest") || (questionnumber.value==30 && subjectName=="FullTest")){
            setNumberKey=null
            questionnumber.value=0
        }
    }

}

    suspend fun getTotalSets(subjectName: String?): Long {
    return try {
        val mDatabaseRef = FirebaseDatabase.getInstance().reference
        if (subjectName != null) {
            val dataSnapshot = mDatabaseRef.child(subjectName).get().await()
            dataSnapshot.childrenCount
        } else {
            0
        }
    } catch (e: Exception) {
        Log.e("getTotalSets", "Error getting total sets: ${e.message}")
        0
    }
}