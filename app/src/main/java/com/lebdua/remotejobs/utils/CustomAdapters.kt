package com.lebdua.remotejobs.utils

import android.net.Uri
import android.view.View
import androidx.databinding.BindingAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

@BindingAdapter("visibleGone")
fun setVisibleGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("frescoImage")
fun setFrescoImage(view: SimpleDraweeView, url: String?) {
    url?.let {
        view.controller = Fresco.newDraweeControllerBuilder()
            .setImageRequest(
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(it))
                    .setProgressiveRenderingEnabled(true)
                    .build())
            .setOldController(view.controller)
            .build()
    }
}