package com.example.testtask.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testtask.R
import com.example.testtask.core.extentions.scope
import com.example.testtask.databinding.FragmentSelectedPhotoBinding

class SelectedPhotoFragment : Fragment(R.layout.fragment_selected_photo) {

    private val binding by viewBinding(FragmentSelectedPhotoBinding::bind)
    private val safeArgs: SelectedPhotoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(root).load(safeArgs.url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .error(R.drawable.ic_image_not_supported)
            .into(ivPhoto)
    }
}