package com.lebdua.remotejobs.feat.jobs

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.model.Job
import com.lebdua.remotejobs.model.vo.Resource

class JobListingViewModel(
    private val context: Context
): ViewModel() {

    val jobs = MutableLiveData<Resource<List<Job>>>()

    fun loadJobs() {
        jobs.value = Resource.loading(context.getString(R.string.job_listing_loading_message))
    }
}