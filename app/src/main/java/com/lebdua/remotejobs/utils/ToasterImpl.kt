package com.lebdua.remotejobs.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.databinding.ToastErrorBinding

class ToasterImpl(
    private val context: Context
) : Toaster {
    override fun showErrorToast(): Toast? {
        return showErrorToast(context.getString(R.string.error_message))
    }

    override fun showErrorToast(text: String): Toast? {
        return DataBindingUtil.bind<ToastErrorBinding>(
            LayoutInflater.from(context)
                .inflate(R.layout.toast_error, null)
        )
            ?.let { binding ->
                binding.customToastText.text = text

                Toast(context).also {
                    it.setGravity(Gravity.BOTTOM, 0, 0)
                    it.duration = Toast.LENGTH_LONG
                    it.view = binding.root
                    it.show()
                }
            }
    }
}