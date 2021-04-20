package dev.timatifey.harmony.data

import dev.timatifey.harmony.common.app.Config.Companion.SPOTIFY_TOKENS_ERROR_CODE
import dev.timatifey.harmony.R
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

object ResponseHandler {

    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data = data)
    }

    fun <T : Any> handleException(exception: Exception? = null, code: Int): Resource<T> {
        return when (exception) {
            is SocketTimeoutException -> Resource.error(msgId = getErrorMessage(ErrorCodes.SocketTimeOut.code))
            else -> Resource.error(msgId = getErrorMessage(code))
        }
    }

    private fun getErrorMessage(code: Int): Int {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> R.string.timeout
            1 -> R.string.auth_failed
            2 -> R.string.invalid_params
            3 -> R.string.ban_user
            4 -> R.string.unknown_method
            5 -> R.string.not_enough_perm
            6 -> R.string.request_send_limit
            7 -> R.string.user_exist
            8 -> R.string.user_not_found
            9 -> R.string.wrong_token
            SPOTIFY_TOKENS_ERROR_CODE -> R.string.spotify_tokens_not_received
            else -> R.string.smth_went_wrong
        }
    }

}