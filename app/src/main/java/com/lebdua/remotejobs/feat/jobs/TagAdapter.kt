package com.lebdua.remotejobs.feat.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lebdua.remotejobs.R
import com.lebdua.remotejobs.databinding.TagItemBinding

class TagAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tags = ArrayList<String>()

    fun setTags(tags: List<String>) {
        this.tags = ArrayList(tags)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TagItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.tag_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? TagItemViewHolder)?.setData(tags[position])
    }

    inner class TagItemViewHolder(
        val binding: TagItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(tag: String) {
            binding.tag = tag
            binding.executePendingBindings()
        }
    }
}