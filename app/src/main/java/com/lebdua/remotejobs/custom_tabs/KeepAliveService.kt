package com.lebdua.remotejobs.custom_tabs

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

@SuppressLint("Registered")
class KeepAliveService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return sBinder
    }

    companion object {
        private val sBinder = Binder()
    }
}