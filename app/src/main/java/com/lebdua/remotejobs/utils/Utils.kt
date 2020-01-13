package com.lebdua.remotejobs.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Run some code on the UI thread after some time
 * @param delay How many milliseconds to wait before executing the code. Default it 1000ms
 * @param doAfterDelay Function to run after the delay
 */
fun runOnUiAfterDelay(delay: Long = 1000L, doAfterDelay: () -> Unit): Disposable {
    return Flowable.timer(delay, TimeUnit.MILLISECONDS)
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            doAfterDelay.invoke()
        }
}

/**
 * @param url url to open
 * @return intent to open an url
 */
private fun getStartUrlIntent(url: String): Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

fun openUrl(
    context: Context,
    url: String,
    failure: () -> Unit
) {
    if (URLUtil.isValidUrl(url)) {
        getStartUrlIntent(url).let {
            if (it.resolveActivity(context.packageManager) != null) {
                context.startActivity(it)
            } else {
                failure.invoke()
            }
        }
    } else {
        failure.invoke()
    }
}