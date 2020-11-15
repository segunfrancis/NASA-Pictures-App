package com.project.segunfrancis.nasapicturesapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.project.segunfrancis.nasapicturesapp.R
import com.project.segunfrancis.nasapicturesapp.databinding.ItemPictureBinding
import com.project.segunfrancis.nasapicturesapp.model.NasaItem
import com.project.segunfrancis.nasapicturesapp.util.loadImage

/**
 * Created by SegunFrancis
 */

class PictureListAdapter(private val imageLoader: ImageLoader) :
    ListAdapter<NasaItem, PictureListAdapter.PictureListViewHolder>(PictureDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return PictureListViewHolder(ItemPictureBinding.bind(view))
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class PictureListViewHolder(private val binding: ItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NasaItem) = with(binding) {
            photoTitle.text = item.title
            photoThumbnail.loadImage(imageLoader, item.url)
        }
    }

    class PictureDiffUtil : DiffUtil.ItemCallback<NasaItem>() {

        override fun areItemsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
            return oldItem.explanation == newItem.explanation
        }


        override fun areContentsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
            return oldItem == newItem
        }

    }
}