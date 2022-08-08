package com.example.jobsearch.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jobsearch.MainActivity
import com.example.jobsearch.adapters.RemoteJobAdapter
import com.example.jobsearch.databinding.FragmentJobBinding
import com.example.jobsearch.utils.Constants
import com.example.jobsearch.viewmodel.RemoteJobViewModel


class JobFragment : Fragment(),SwipeRefreshLayout.OnRefreshListener {

    lateinit var binding: FragmentJobBinding
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var remoteJobAdapter: RemoteJobAdapter
    private lateinit var swipeLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobBinding.inflate(layoutInflater)

        swipeLayout = binding.swipeContainer
        swipeLayout.setOnRefreshListener(this)
        swipeLayout.setColorSchemeColors(Color.GREEN, Color.RED, Color.BLUE, Color.DKGRAY)
        swipeLayout.post {
            swipeLayout.isRefreshing = true
            setUpRecyclerView()
        }
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
        if (Constants.isNetworkAvailable(requireContext())) {
            viewModel.remoteJobResult().observe(viewLifecycleOwner) { remetojob ->
                if (remetojob != null) {
                    remoteJobAdapter.differ.submitList(remetojob.jobs)
                    swipeLayout.isRefreshing = false

                } else {
                    Toast.makeText(activity, "no internet", Toast.LENGTH_LONG).show()
                    swipeLayout.isRefreshing = false
                }
            }
        }

    }

    override fun onRefresh() {
        setUpRecyclerView()
    }


}