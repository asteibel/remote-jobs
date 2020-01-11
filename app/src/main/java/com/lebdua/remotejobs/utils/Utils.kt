package com.lebdua.remotejobs.utils

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