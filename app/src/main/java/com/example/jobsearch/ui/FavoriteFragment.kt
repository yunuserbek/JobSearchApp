package com.example.jobsearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobsearch.MainActivity
import com.example.jobsearch.adapters.FavJobAdapter
import com.example.jobsearch.databinding.FragmentFavoriteBinding
import com.example.jobsearch.model.FavoriteJob
import com.example.jobsearch.models.viewmodel.RemoteJobViewModel


class FavoriteFragment : Fragment() {
    lateinit var binding:FragmentFavoriteBinding
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var favAdapter: FavJobAdapter
   
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as MainActivity).viewModel
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        favAdapter= FavJobAdapter()
        binding.rvJobsSaved.layoutManager =LinearLayoutManager(activity)
        binding.rvJobsSaved.setHasFixedSize(true)
        binding.rvJobsSaved.addItemDecoration(object :DividerItemDecoration(activity,LinearLayoutManager.VERTICAL){})
        binding.rvJobsSaved.adapter = favAdapter
        viewModel.getAllFavJobs().observe(viewLifecycleOwner){favJob->
            favAdapter.differ.submitList(favJob)
            updateUI(favJob)


        }
    }

    private fun updateUI(favJob: List<FavoriteJob>?) {
        if (favJob!!.isNotEmpty()){
            binding.rvJobsSaved.visibility =View.VISIBLE
            binding.cardNoAvailable.visibility = View.GONE
        }else{
            binding.rvJobsSaved.visibility =View.GONE
            binding.cardNoAvailable.visibility = View.VISIBLE
        }
    }


}