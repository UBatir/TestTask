package com.example.testtask.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.testtask.R
import com.example.testtask.databinding.FragmentSelectedPhotoBinding

class SelectedPhotoFragment:Fragment(R.layout.fragment_selected_photo) {

    private lateinit var binding: FragmentSelectedPhotoBinding
    private val safeArgs:SelectedPhotoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectedPhotoBinding.bind(view)
        val url=safeArgs.url
        Glide.with(this)
            .load(url)
            .error(R.drawable.image)
            .transform(CenterInside(), RoundedCorners(20))
            .into(binding.ivPhoto)
    }
}