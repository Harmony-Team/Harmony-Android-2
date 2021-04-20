package dev.timatifey.harmony.util

import android.util.Patterns

object Validator {

    // A placeholder username validation check
    fun isUserNameValid(username: String): Boolean {
        return username.length > 5
    }

    // A placeholder password validation check
    fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun isEmailValid(text: String): Boolean {
        return if (text.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(text).matches()
        } else {
            text.isNotBlank()
        }
    }
}