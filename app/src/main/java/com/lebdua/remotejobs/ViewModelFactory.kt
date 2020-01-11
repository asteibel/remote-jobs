package com.lebdua.remotejobs

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lebdua.remotejobs.feat.jobs.JobListingViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when  {
            modelClass.isAssignableFrom(JobListingViewModel::class.java) -> {
                JobListingViewModel(
                    context
                ) as T
            }
            else -> throw IllegalArgumentException("Can't find ViewModel")
        }
    }
}
