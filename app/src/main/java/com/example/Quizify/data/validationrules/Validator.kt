package com.example.Quizify.data.validationrules

object Validator {
    fun validateName(name:String): ValidationResult{
        return ValidationResult(
            (!name.isNullOrEmpty() && name.length>=3)
        )
    }

    fun validateEmail(email:String): ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty() )
        )
    }

    fun validatePhone(phone:String): ValidationResult{
        return ValidationResult(
            (!phone.isNullOrEmpty() && phone.length==10)
        )
    }

    fun validatePassword(password:String): ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length>=8)
        )
    }

    fun privacyPolicyAcceptance(statusvalue:Boolean):ValidationResult{
        return ValidationResult(
            !statusvalue
        )
    }
}

data class ValidationResult(
    val status:Boolean=false
)