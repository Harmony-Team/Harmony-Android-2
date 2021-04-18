package dev.timatifey.harmony.util

import android.annotation.SuppressLint
import android.util.Base64
import java.security.MessageDigest
import kotlin.streams.asSequence
import java.util.Random

@SuppressLint("NewApi")
fun randomString(beginLength: Int, endLength: Int): String {
    val random = Random()
    val source = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return random.ints(
        (random.nextInt(endLength - beginLength + 1) + beginLength).toLong(),
        0, source.size)
        .asSequence()
        .map(source::get)
        .joinToString("")
}

fun String.hashBySHA256WithBase64Encode(): String  {
    val digester = MessageDigest.getInstance("SHA-256")
    digester.update(this.toByteArray())
    return Base64.encodeToString(digester.digest(), Base64.NO_WRAP)
        .replace('+', '-')
        .replace('/', '_')
        .trimEnd('=')
}