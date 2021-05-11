package dev.timatifey.harmony.app

class Config {
    companion object {
        const val BASE_URL = ""
        const val SUCCESS_CODE = 0
        const val SPOTIFY_TOKENS_ERROR_CODE = 20

        const val PREFERENCE_NAME = "my_preference"
        const val APP_PREFERENCES_TOKEN = "Token"
        const val APP_PREFERENCES_PASSWORD = "Password"
        const val APP_PREFERENCES_IS_FIRST_OPEN = "isOpenAfterInstall"

        const val SPOTIFY_AUTH_BASE_URL = "https://accounts.spotify.com"
        const val SPOTIFY_API_BASE_URL = "https://api.spotify.com"
        const val SPOTIFY_REDIRECT_URI = ""
        const val SPOTIFY_CLIENT_ID = ""

        const val SHARING_REQUEST_CODE = 3

        val SPOTIFY_SCOPES = listOf(
            "app-remote-control",
            "user-library-read",
            "user-read-email",
            "playlist-read-private"
        )
            .joinToString(separator = " ")
    }
}