package com.lebdua.remotejobs.utils

import android.content.Context
import android.util.Log
import com.lebdua.remotejobs.R
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    private const val INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    private fun getFormatter(
        format: String
    ): SimpleDateFormat = SimpleDateFormat(format, Locale.getDefault())

    fun getTimeAgo(
        context: Context,
        date: String,
        inputFormat: String = INPUT_FORMAT
    ): String {
        return try {
            getFormatter(inputFormat).parse(date)!!.let { dateAsDate ->
                val diff = (System.currentTimeMillis() - dateAsDate.time) / 1000L
                (diff / 60L / 60L).toInt().let { hoursAgo ->
                    if (hoursAgo < 24) {
                        context.resources.getQuantityString(
                            R.plurals.posted_hours_ago,
                            hoursAgo,
                            hoursAgo
                        )
                    } else {
                        (diff / 60L / 60L / 24L).toInt().let { daysAgo ->
                            context.resources.getQuantityString(
                                R.plurals.posted_days_ago,
                                daysAgo,
                                daysAgo
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("DateHelper", "Can't get time ago for $date: ${e.message}")
            return date
        }
    }
}