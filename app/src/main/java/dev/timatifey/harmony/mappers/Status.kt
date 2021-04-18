package dev.timatifey.harmony.mappers

sealed class Status {
    object Success: Status()
    object Error: Status()
}