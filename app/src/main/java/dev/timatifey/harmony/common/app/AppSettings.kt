package dev.timatifey.harmony.common.app

import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.service.AuthService
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSettings @Inject constructor(
    authService: AuthService
) {
    var isAuthorized: Boolean = false

    var harmonyToken: Token? = null
        private set

    init {
        runBlocking {
            val authResult = authService.authCacheIsValid()
            harmonyToken = authResult.data
            isAuthorized = authResult.status is Status.Success
        }
    }
}