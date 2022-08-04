package com.example.jobsearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobsearch.MainActivity
import com.example.jobsearch.adapters.RemoteJobAdapter
import com.example.jobsearch.databinding.FragmentJobBinding
import com.example.jobsearch.models.viewmodel.RemoteJobViewModel
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter


class JobFragment : Fragment() {

lateinit var binding: FragmentJobBinding
private lateinit var viewModel: RemoteJobViewModel
private lateinit var remoteJobAdapter: RemoteJobAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        remoteJobAdapter = RemoteJobAdapter()
        binding.rvRemoteJobs.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(
                object : DividerItemDecoration(
                    activity, LinearLayout.VERTICAL
                ) {})
            adapter = remoteJobAdapter

        }
        fetchingData()

    }

    private fun fetchingData() {
        viewModel.remoteJobResult().observe(viewLifecycleOwner){remetojob->
            if (remetojob != null){
                remoteJobAdapter.differ.submitList(remetojob.jobs)

            }


        }
    }


}