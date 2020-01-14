package com.lebdua.remotejobs.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

fun SpannableString.setTypefaceSpannable(
    context: Context,
    fontId: Int,
    text: String
): SpannableString {
    val typeface = ResourcesCompat.getFont(context, fontId)
    if (text in this && typeface != null)
        setSpan(
            CustomTypefaceSpan(
                "",
                typeface
            ),
            indexOf(text), indexOf(text) + text.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    return this
}

fun SpannableString.setClickableSpan(
    text: String,
    underlineText: Boolean = false,
    onClick: () -> Unit
): SpannableString {
    if (text in this)
        setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    onClick.invoke()
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = underlineText
                }
            },
            indexOf(text),
            indexOf(text) + text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    return this
}

fun SpannableString.setColorSpan(
    context: Context,
    color: Int,
    text: String
) {
    if (text in this)
        setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, color)),
            indexOf(text), indexOf(text) + text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
}

class CustomTypefaceSpan(family: String, private val newType: Typeface) : TypefaceSpan(family) {

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType)
    }

    private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
        val oldStyle: Int
        val old = paint.typeface
        oldStyle = old?.style ?: 0

        val fake = oldStyle and tf.style.inv()
        if (fake and Typeface.BOLD !== 0) {
            paint.isFakeBoldText = true
        }

        if (fake and Typeface.ITALIC !== 0) {
            paint.textSkewX = -0.25f
        }

        paint.typeface = tf
    }
}