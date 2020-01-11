package com.lebdua.remotejobs.feat.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.databinding.JobItemBinding
import com.lebdua.remotejobs.model.Job
import com.lebdua.remotejobs.utils.DateHelper

class JobAdapter(
    private val interactions: JobItemInteractions
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobs = ArrayList<Job>()

    fun setJobs(jobs: List<Job>) {
        this.jobs = ArrayList(jobs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return JobItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.job_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = jobs.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? JobItemViewHolder)?.setData(jobs[position])
    }

    inner class JobItemViewHolder(
        private val binding: JobItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(job: Job) {
            binding.job = job
            binding.postedDate = DateHelper.getTimeAgo(
                binding.root.context,
                job.date
            )

            val tagAdapter = TagAdapter()
            binding.tagsRv.layoutManager = object : FlexboxLayoutManager(binding.root.context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }.apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
            binding.tagsRv.adapter = tagAdapter

            tagAdapter.setTags(job.tags)

            binding.interactions = interactions
            binding.executePendingBindings()
        }
    }

    interface JobItemInteractions {

        fun openJob(job: Job)
    }
}
