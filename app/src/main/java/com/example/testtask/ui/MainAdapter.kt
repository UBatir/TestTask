package com.example.testtask.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testtask.R
import com.example.testtask.core.extentions.inflate
import com.example.testtask.core.extentions.onClick
import com.example.testtask.databinding.ItemPhotoBinding


class MainAdapter:RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    var models:MutableList<String> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    private var onItemClick: (url:String) -> Unit = {  }
    fun setOnClickItemListener(onItemClick: (url:String) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class MainViewHolder(private val binding: ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetJavaScriptEnabled")
        fun populateModel(model:String){
            var url = if (model[5]!='s') model.replace("http","https")
            else model
            url=url.dropLast(1).drop(1)
            binding.root.onClick {
                onItemClick.invoke(url)
            }
            Glide.with(binding.root)
                .load(url)
                .error(R.drawable.image)
                .centerCrop()
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView=parent.inflate(R.layout.item_photo)
        val binding=ItemPhotoBinding.bind(itemView)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int=models.size
}