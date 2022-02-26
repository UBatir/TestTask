package com.example.testtask.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtask.R
import com.example.testtask.core.extentions.scope
import com.example.testtask.databinding.FragmentMainBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val adapter: MainAdapter by inject()
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getImages()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getImages()
            swipeRefreshLayout.isRefreshing=false
        }
        recyclerView.adapter =adapter
        adapter.setOnClickItemListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSelectedPhotoFragment(it))
        }
        setUpObserver()
    }

    private fun setUpObserver(){
        viewModel.success.observe(viewLifecycleOwner,{
            adapter.models=it.toMutableList()
        })
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

    }
}