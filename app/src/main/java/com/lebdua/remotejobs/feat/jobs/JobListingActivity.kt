package com.lebdua.remotejobs.feat.jobs

import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.custom_tabs.CustomTabBaseActivity
import com.lebdua.remotejobs.databinding.ActivityJobListingBinding
import com.lebdua.remotejobs.di.RemoteJobsComponentProvider
import com.lebdua.remotejobs.model.Job
import com.lebdua.remotejobs.model.vo.Status
import javax.inject.Inject

class JobListingActivity : CustomTabBaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: JobListingViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[JobListingViewModel::class.java]
    }

    private val jobItemInteractions = object : JobAdapter.JobItemInteractions {
        override fun openJob(job: Job) {
            openCustomTab(
                getRemoteJobsCustomTabsIntentBuilder(this@JobListingActivity).build(),
                Uri.parse(job.url)
            )
        }
    }

    private val jobAdapter: JobAdapter by lazy {
        JobAdapter(jobItemInteractions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityJobListingBinding>(
            this,
            R.layout.activity_job_listing
        )

        binding.interactions = object : JobListingInteractions {
            override fun retry() {
                viewModel.retryLoadJobs()
            }
        }

        binding.jobsRv.layoutManager = LinearLayoutManager(this)
        binding.jobsRv.adapter = jobAdapter

        viewModel.jobs.observe(this, Observer {
            binding.jobsResource = it
            if (it.status == Status.SUCCESS) {
                it.data?.let { jobs ->
                    jobAdapter.setJobs(jobs)
                }
            }
        })

        viewModel.loadJobs()
    }

    override fun inject() {
        (application as RemoteJobsComponentProvider).provideRemoteJobsComponent()
            .inject(this)
    }
}
