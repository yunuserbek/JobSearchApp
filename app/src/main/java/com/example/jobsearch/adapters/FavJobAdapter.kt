package com.example.jobsearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearch.databinding.JobLayoutAdapterBinding
import com.example.jobsearch.model.FavoriteJob
import com.example.jobsearch.model.Job
import com.example.jobsearch.ui.MainFragmentDirections

class FavJobAdapter : RecyclerView.Adapter<FavJobAdapter.RemoJobViewHolder>() {
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
            val job = Job(
                candidateRequiredLocation = currentJob.candidateRequiredLocation,
                category = currentJob.category,
                companyLogo = currentJob.companyLogo,
                companyLogoUrl = currentJob.companyLogoUrl,
                companyName = currentJob.companyName,
                description = currentJob.description,
                jobId = currentJob.jobId,
                jobType = currentJob.publicationDate,
                publicationDate = currentJob.title,
                salary = currentJob.url,
                title = currentJob.title,
                url = currentJob.url
            )
            val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(job)
            mView.findNavController().navigate(direction)


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}

private val differCallback = object :
    DiffUtil.ItemCallback<FavoriteJob>() {
    override fun areItemsTheSame(oldItem: FavoriteJob, newItem: FavoriteJob): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FavoriteJob, newItem: FavoriteJob): Boolean {
       return oldItem == newItem
    }


}