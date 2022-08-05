package com.example.jobsearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobsearch.databinding.FragmentFavoriteBinding
import com.example.jobsearch.models.viewmodel.RemoteJobViewModel


class FavoriteFragment : Fragment() {
    lateinit var binding:FragmentFavoriteBinding
    private lateinit var viewModel: RemoteJobViewModel
   
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }


}