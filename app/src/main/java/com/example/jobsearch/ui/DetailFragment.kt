package com.example.jobsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.jobsearch.MainActivity
import com.example.jobsearch.databinding.FragmentDetailBinding
import com.example.jobsearch.model.FavoriteJob
import com.example.jobsearch.model.Job
import com.example.jobsearch.models.viewmodel.RemoteJobViewModel
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private lateinit var currentJob: Job
    private lateinit var viewModel: RemoteJobViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        currentJob = args.job!!
        setUpWebView()
        binding.fabAddFavorite.setOnClickListener {
            addFavJob(view)
        }
    }

    private fun addFavJob(view: View) {
        val favJob = FavoriteJob(
            0,
            currentJob.candidateRequiredLocation,
            currentJob.category,
            currentJob.companyLogoUrl,
            currentJob.description,
            currentJob.jobType,
            currentJob.description,
            currentJob.jobId,
            currentJob.salary,
            currentJob.title,
            currentJob.url,
            currentJob.publicationDate,
            currentJob.url

        )
        viewModel.addFavJob(favJob)
        Snackbar.make(view, "job saved  successfully", Snackbar.LENGTH_LONG).show()
    }

    private fun setUpWebView() {
        binding.webView.webViewClient = WebViewClient()
        currentJob.url?.let {
            binding.webView.loadUrl(it)
        }
    }
}