package dev.timatifey.harmony.common.app

class Config {
    companion object {
        const val BASE_URL = ""
        const val SUCCESS_CODE = 0
        const val SPOTIFY_TOKENS_ERROR_CODE = 20

        const val PREFERENCE_NAME = ""
        const val APP_PREFERENCES_TOKEN = ""
        const val APP_PREFERENCES_PASSWORD = ""
        const val APP_PREFERENCES_IS_FIRST_OPEN = ""

        const val SPOTIFY_AUTH_BASE_URL = ""
        const val SPOTIFY_API_BASE_URL = ""
        const val SPOTIFY_REDIRECT_URI = ""
        const val SPOTIFY_CLIENT_ID = ""

        val SPOTIFY_SCOPES = listOf("")
            .joinToString(separator = " ")
    }
}