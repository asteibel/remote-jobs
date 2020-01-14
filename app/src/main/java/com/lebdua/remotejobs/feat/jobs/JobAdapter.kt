package com.lebdua.remotejobs.feat.jobs

import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.databinding.JobItemBinding
import com.lebdua.remotejobs.databinding.JobListHeaderItemBinding
import com.lebdua.remotejobs.model.Job
import com.lebdua.remotejobs.utils.*

class JobAdapter(
    private val interactions: JobItemInteractions
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobs = ArrayList<Job>()

    fun setJobs(jobs: List<Job>) {
        this.jobs = ArrayList(jobs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER)
            JobListHeaderViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.job_list_header_item,
                    parent,
                    false
                )
            )
        else
            JobItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.job_item,
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int = jobs.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> {
                (holder as? JobListHeaderViewHolder)?.setData()
            }
            TYPE_JOB -> {
                (holder as? JobItemViewHolder)?.setData(jobs[position - 1])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER
        else TYPE_JOB
    }

    inner class JobListHeaderViewHolder(
        private val binding: JobListHeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData() {
            binding.root.context.let {
                binding.text = SpannableString(it.getString(R.string.job_list_header_text)).apply {
                    val remoteokLink = it.getString(R.string.remoteeok_link)
                    setTypefaceSpannable(it, R.font.roboto_medium, remoteokLink)
                    setClickableSpan(remoteokLink) {
                        openUrl(it, "https://remoteok.io/")
                    }
                    setColorSpan(it, R.color.colorPrimary, remoteokLink)
                }
            }
            binding.headerTv.movementMethod = LinkMovementMethod.getInstance()
        }
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

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_JOB = 1
    }
}
