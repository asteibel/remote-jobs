package com.lebdua.remotejobs.feat.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.databinding.JobItemBinding
import com.lebdua.remotejobs.model.Job

class JobAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    class JobItemViewHolder(
        private val binding: JobItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(job: Job) {
            binding.job = job
            binding.executePendingBindings()
        }
    }
}
