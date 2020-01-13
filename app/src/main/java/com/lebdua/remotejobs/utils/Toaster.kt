package com.lebdua.remotejobs.utils

import android.widget.Toast

/**
 * Utility class to create and display various toasts.
 */
interface Toaster {
    fun showErrorToast(): Toast?
    fun showErrorToast(text: String): Toast?
}