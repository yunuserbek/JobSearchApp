package com.example.jobsearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearch.databinding.JobLayoutAdapterBinding
import com.example.jobsearch.models.Job
import com.example.jobsearch.ui.MainFragmentDirections

class RemoteJobAdapter : RecyclerView.Adapter<RemoteJobAdapter.RemoJobViewHolder>() {
    private var binding: JobLayoutAdapterBinding? = null

    inner class RemoJobViewHolder(itemBinding: JobLayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoJobViewHolder {
        binding =
            JobLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RemoJobViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RemoJobViewHolder, position: Int) {
        val currentJob = differ.currentList[position]
        holder.itemView.apply {
            binding?.tvCompanyName?.text = currentJob.companyName
            binding?.tvJobLocation?.text = currentJob.candidateRequiredLocation
            binding?.tvJobTitle?.text = currentJob.title
            binding?.tvJobType?.text = currentJob.jobType
            val dateJob = currentJob.publicationDate?.split("T")
            binding?.tvDate?.text = dateJob?.get(0)
        }.setOnClickListener { mView ->
            val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(currentJob)
            mView.findNavController().navigate(direction)


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}

private val differCallback = object :
    DiffUtil.ItemCallback<Job>() {
    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem == newItem
    }

}