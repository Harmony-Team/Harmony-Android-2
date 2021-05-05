package dev.timatifey.harmony.util

import android.content.Intent

fun generateShareLink(id: Long): String {
    return "https://harmony/${id.toString().hashBySHA256WithBase64Encode()}"
}

fun createShareLinkIntent(code: String): Intent {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_SUBJECT, "Share link")
    intent.putExtra(Intent.EXTRA_TEXT, code)
    return intent
}