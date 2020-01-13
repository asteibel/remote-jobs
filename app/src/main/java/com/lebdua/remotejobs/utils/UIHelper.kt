package com.lebdua.remotejobs.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat

/**
 * Return a bitmap from a xml
 * @throws Exception if drawable is invalid
 */
fun getBitmap(context: Context, drawableRes: Int): Bitmap {
    val drawable = ContextCompat.getDrawable(context, drawableRes)
    val canvas = Canvas()
    if (drawable == null) throw Exception("Cannot get Bitmap: invalid drawable")
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    canvas.setBitmap(bitmap)
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    drawable.draw(canvas)

    return bitmap
}