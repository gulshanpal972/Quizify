package com.example.Quizify.activities

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Quizify.GlobalVariable
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.navigation.SystemBackButtonHandler
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun AddSet(addsetviewmodel: AddSetHandler = viewModel(), selectedSubject: String?) {
    var nextSetNumber by remember { mutableStateOf(0L) }

    LaunchedEffect(selectedSubject) {
        nextSetNumber = getTotalSets(selectedSubject) + 1
        Log.d("AddSet", "GlobalVariable.totalQuestions: ${GlobalVariable.totalQuestions}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Add Set $nextSetNumber",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    addsetviewmodel.onEvent(
                        AddSetUIEvents.AddSets(selectedSubject)
                    )
                    // Perform your other actions
                    Quizapprouter.navigateTo(Screen.AddQuestions)
                },
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(100.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF9DCEFF),
                                    Color(0xFF92A3FD)
                                )
                            ),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "AddQuestions",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
    SystemBackButtonHandler()
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

data class AddSetUIState(
    var subjectName: String? = null
)

sealed class AddSetUIEvents {
    data class AddSets(val subjectName: String?) : AddSetUIEvents()
}

class AddSetHandler : ViewModel() {

    private val TAG = AddSetHandler::class.simpleName
    var setuistate = mutableStateOf(AddSetUIState())

    fun onEvent(event: AddSetUIEvents) {
        when (event) {
            is AddSetUIEvents.AddSets -> {
                if (event.subjectName != null) {
                    setuistate.value = setuistate.value.copy(
                        subjectName = event.subjectName  // Update subjectName in UI state
                    )
                } else {
                    Log.e(TAG, "SubjectName is null when calling AddSetDB")
                }
            }
        }
    }
}
