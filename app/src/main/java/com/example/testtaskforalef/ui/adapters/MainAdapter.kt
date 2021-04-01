package com.example.testtaskforalef.ui.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testtaskforalef.R
import com.example.testtaskforalef.databinding.ItemCardImageBinding

class MainAdapter(
    private val itemClickListener: ItemClickListener<String>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var dataList: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class ViewHolder(val binding: ItemCardImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataImage: String) {
            Glide.with(itemView)
                .load(dataImage)
                .listener(object : RequestListener<Drawable> {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.itemCardImageEivImage.background =
                            itemView.resources.getDrawable(R.drawable.image_warning, null)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        return false
                    }

                })
                .into(binding.itemCardImageEivImage)


            binding.itemCardImageContainer.setOnClickListener {
                itemClickListener.onClick(dataImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemCardImageBinding = ItemCardImageBinding.inflate(inflater, parent, false)
        return ViewHolder(itemCardImageBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList.get(position))
    }

    override fun getItemCount() = dataList.size
}