package dev.timatifey.harmony.util

fun generateShareLink(id: Long): String {
    return "https://harmony/${id.toString().hashBySHA256WithBase64Encode()}"
}