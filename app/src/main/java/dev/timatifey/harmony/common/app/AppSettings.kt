package dev.timatifey.harmony.common.app

import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.service.AuthService
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSettings @Inject constructor(
    authService: AuthService
) {
    val isAuthorized: Boolean
        get() = harmonyToken != null

    var harmonyToken: Token? = null
        private set

    init {
        runBlocking {
            harmonyToken = authService.authCacheIsValid().data
        }
    }
}