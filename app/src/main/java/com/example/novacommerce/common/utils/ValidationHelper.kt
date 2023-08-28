package com.example.novacommerce.common.utils

import android.util.Patterns

object ValidationHelper {

    fun singlePasswordValidation(password : String) : ValidationResult{
        return if (password.length < 6 || password.length > 20){
            ValidationResult.Error("Password length should be in between 6 to 20 characters")
        }else {
            ValidationResult.Success
        }
    }

    fun mailValidation(mail : String) : ValidationResult{
        return if (mail.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            ValidationResult.Success
        }else{
            ValidationResult.Error("Mail address is badly formatted")
        }
    }

    fun passwordMatchValidation(password : String, passwordAgain : String) : ValidationResult{
        return if (password == passwordAgain){
            val result1 = singlePasswordValidation(password)
            val result2 = singlePasswordValidation(passwordAgain)
          if (result1 == result2 && result2 is ValidationResult.Success){
              ValidationResult.Success
          }else{
              result1
          }
        }else{
            ValidationResult.Error("Passwords do not match")
        }
    }
}

sealed class ValidationResult{
    data class Error(val message : String) : ValidationResult()
    object Success : ValidationResult()
}