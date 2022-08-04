package com.example.jobsearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.jobsearch.databinding.FragmentDetailBinding
import com.example.jobsearch.models.Job


class DetailFragment : Fragment() {
    lateinit var binding:FragmentDetailBinding
    private lateinit var currentJob:Job
    private val args:DetailFragmentArgs by navArgs()

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
        currentJob =args.job!!
        setUpWebView()
    }

    private fun setUpWebView() {
        binding.webView.webViewClient = WebViewClient()
        currentJob.url?.let {
            binding.webView.loadUrl(it)
        }
    }
}