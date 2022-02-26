package com.example.testtask.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testtask.R
import com.example.testtask.core.extentions.inflate
import com.example.testtask.core.extentions.onClick
import com.example.testtask.databinding.ItemPhotoBinding

@SuppressLint("NotifyDataSetChanged")
class MainAdapter:RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    var models:MutableList<String> = mutableListOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    private var onItemClick: (url:String) -> Unit = {  }
    fun setOnClickItemListener(onItemClick: (url:String) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class MainViewHolder(private val binding: ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){
        fun populateModel(model:String){
            val url = if (model[4]!='s') model.replace("http","https")
            else model
            Glide.with(binding.root)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .centerCrop()
                .error(R.drawable.ic_image_not_supported)
                .into(binding.ivPhoto)
            binding.root.onClick {
                onItemClick.invoke(url)
            }
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