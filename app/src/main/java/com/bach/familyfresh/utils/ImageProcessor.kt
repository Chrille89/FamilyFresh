package com.bach.familyfresh.utils

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

fun decodeBase64ToBitmap(base64String: String) = try {
    val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
    val imageBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)?.asImageBitmap()
    imageBitmap ?: throw IllegalArgumentException("Could not decode image")
} catch (e: Exception) {
    throw e
}