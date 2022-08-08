package com.example.jobsearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobsearch.MainActivity
import com.example.jobsearch.adapters.RemoteJobAdapter
import com.example.jobsearch.databinding.FragmentSearchBinding
import com.example.jobsearch.utils.Constants
import com.example.jobsearch.viewmodel.RemoteJobViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.zip.ZipEntry


class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var jobAdapter: RemoteJobAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        if (Constants.isNetworkAvailable(requireContext())){
            searchJob()
            setUpRecyclerView()
        }else{
            Toast.makeText(activity,"no Internet",Toast.LENGTH_LONG).show()

        }

    }

    private fun searchJob() {
        var job : Job? =null
        binding.etSearch.addTextChangedListener{text->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                text?.let {
                    if (text.toString().isNotEmpty()){
                        viewModel.searchRemote(text.toString())
                    }
                }
            }
        }
    }
    private fun setUpRecyclerView(){
        jobAdapter = RemoteJobAdapter()
        binding.rvSearchJobs.apply {
            layoutManager= LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = jobAdapter
        }
        viewModel.searchResult().observe(viewLifecycleOwner){remotejob->
            jobAdapter.differ.submitList(remotejob.jobs)
        }
    }

}