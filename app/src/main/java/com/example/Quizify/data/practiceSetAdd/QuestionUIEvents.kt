package com.example.Quizify.data.practiceSetAdd

sealed class QuestionUIEvents{
    data class NumberOfQuestions(var numberOfQuestions:String):QuestionUIEvents()
    data class Duration(var duration: String):QuestionUIEvents()
    data class Question(var question:String):QuestionUIEvents()
    data class Option1(var option1:String):QuestionUIEvents()
    data class Option2(var option2:String):QuestionUIEvents()
    data class Option3(var option3:String):QuestionUIEvents()
    data class Option4(var option4:String):QuestionUIEvents()
    data class Answer(var answer:String):QuestionUIEvents()

    object Upload: QuestionUIEvents()
}
