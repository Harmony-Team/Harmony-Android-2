package dev.timatifey.harmony.data

sealed class Status {
    object Success: Status()
    object Error: Status()
}