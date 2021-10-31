package com.example.testtask.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testtask.Helper
import com.example.testtask.R
import com.example.testtask.core.ResourceState
import com.example.testtask.core.extentions.visibility
import com.example.testtask.databinding.FragmentMainBinding
import org.koin.android.ext.android.inject

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var binding:FragmentMainBinding
    private val adapter: MainAdapter by inject()
    private lateinit var navController: NavController
    private lateinit var helper:Helper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        binding=FragmentMainBinding.bind(view)
        helper= Helper(requireContext())
        helper.getDataFromUrl()
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                helper.getDataFromUrl()
            }
            recyclerView.adapter =adapter
            adapter.setOnClickItemListener {
                navController.navigate(MainFragmentDirections.actionMainFragmentToSelectedPhoto(it))
            }
        }
        setUpObserver()
    }

    private fun setUpObserver(){
        helper.list.observe(viewLifecycleOwner,{
            when(it.status){
                ResourceState.LOADING-> {
                    binding.swipeRefreshLayout.isRefreshing =true
                    binding.progressBar.visibility(true)
                }
                ResourceState.SUCCESS->{
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.progressBar.visibility(false)
                    adapter.models=it.data!!
                }
                ResourceState.ERROR-> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.progressBar.visibility(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}