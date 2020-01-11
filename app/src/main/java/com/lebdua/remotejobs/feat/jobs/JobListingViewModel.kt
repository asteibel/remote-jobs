package com.lebdua.remotejobs.feat.jobs

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.model.Job
import com.lebdua.remotejobs.model.vo.Resource
import com.lebdua.remotejobs.networking.repositories.ApiRepository
import com.lebdua.remotejobs.utils.runOnUiAfterDelay

class JobListingViewModel(
    private val context: Context,
    private val apiRepository: ApiRepository
) : ViewModel() {

    val jobs = MutableLiveData<Resource<List<Job>>>()

    fun loadJobs() {
        jobs.value = Resource.loading(context.getString(R.string.job_listing_loading_message))
        apiRepository.getJobs(
            {
                jobs.value = Resource.success(it)
                Log.d(TAG, "jobs: $it")
            },
            {
                jobs.value = Resource.error(context.getString(R.string.error_message))
                Log.e(TAG, "Can't get jobs: ${it.message}")
            }
        )
    }

    fun retryLoadJobs() {
        jobs.value = Resource.loading(context.getString(R.string.job_listing_loading_message))
        runOnUiAfterDelay {
            loadJobs()
        }
    }

    companion object {
        private const val TAG = "JobListingVM"
    }
}