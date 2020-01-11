package com.lebdua.remotejobs.feat.jobs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.databinding.ActivityJobListingBinding
import com.lebdua.remotejobs.di.RemoteJobsComponentProvider
import com.lebdua.remotejobs.model.vo.Status
import javax.inject.Inject

class JobListingActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: JobListingViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[JobListingViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        val binding = DataBindingUtil.setContentView<ActivityJobListingBinding>(this, R.layout.activity_job_listing)

        viewModel.jobs.observe(this, Observer {
            binding.message = when (it.status) {
                Status.SUCCESS -> "success"
                Status.LOADING -> "loading"
                Status.ERROR -> "error"
            }
        })

        viewModel.loadJobs()
    }

    private fun inject() {
        (application as RemoteJobsComponentProvider).provideRemoteJobsComponent()
            .inject(this)
    }
}
