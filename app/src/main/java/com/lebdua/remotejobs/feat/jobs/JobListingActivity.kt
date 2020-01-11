package com.lebdua.remotejobs.feat.jobs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.di.RemoteJobsComponentProvider
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

        setContentView(R.layout.ativity_job_listing)

        viewModel.jobs.observe(this, Observer {
            Log.d("JobListingActivity", "status: ${it.status}")
        })

        viewModel.loadJobs()
    }

    private fun inject() {
        (application as RemoteJobsComponentProvider).provideRemoteJobsComponent()
            .inject(this)
    }
}
