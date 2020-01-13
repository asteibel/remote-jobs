package com.lebdua.remotejobs.custom_tabs

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.base.BaseActivity
import com.lebdua.remotejobs.utils.Toaster
import com.lebdua.remotejobs.utils.getBitmap
import com.lebdua.remotejobs.utils.openUrl
import javax.inject.Inject


abstract class CustomTabBaseActivity : BaseActivity(), CustomTabActivityHelper.ConnectionCallback {

    @Inject
    lateinit var customTabActivityHelper: CustomTabActivityHelper

    @Inject
    lateinit var toaster: Toaster

    fun mayLaunchUrl(uri: Uri) {
        customTabActivityHelper.mayLaunchUrl(uri)
    }

    fun openCustomTab(
        customTabsIntent: CustomTabsIntent,
        uri: Uri
    ) {
        customTabActivityHelper.openCustomTab(
            this,
            customTabsIntent,
            uri,
            object : CustomTabActivityHelper.CustomTabFallback {
                override fun openUri(activity: Activity, uri: Uri) {
                    openUrl(this@CustomTabBaseActivity, uri.toString()) {
                        toaster.showErrorToast()
                    }
                }
            })
    }

    /**
     * @param showTitle whether to show the title in the toolbar or not
     * @return a CustomTabsIntent.Builder that has the Hosco blue as a toolbar color and a white arrow back as a close button
     */
    fun getRemoteJobsCustomTabsIntentBuilder(
        context: Context,
        showTitle: Boolean = true
    ): CustomTabsIntent.Builder =
        CustomTabsIntent.Builder().let {
            it.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            it.setShowTitle(showTitle)
            it.setCloseButtonIcon(getBitmap(context, R.drawable.ic_arrow_left))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customTabActivityHelper.setConnectionCallback(this)
    }

    override fun onCustomTabsConnected() {
        Log.d("CustomTabBaseActivity", "custom tabs connected")
    }

    override fun onCustomTabsDisconnected() {
        Log.d("CustomTabBaseActivity", "custom tabs disconnected")
    }

    override fun onStart() {
        super.onStart()
        customTabActivityHelper.bindCustomTabsService(this)
    }

    override fun onStop() {
        super.onStop()
        customTabActivityHelper.unbindCustomTabsService(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        customTabActivityHelper.setConnectionCallback(null)
    }
}
