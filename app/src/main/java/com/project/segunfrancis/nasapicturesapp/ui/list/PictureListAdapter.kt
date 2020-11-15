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

class PictureListAdapter(
    private val imageLoader: ImageLoader,
    private val click: (position: Int) -> Unit
) :
    ListAdapter<NasaItem, PictureListAdapter.PictureListViewHolder>(PictureDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return PictureListViewHolder(ItemPictureBinding.bind(view), imageLoader, click)
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class PictureListViewHolder(
        private val binding: ItemPictureBinding,
        private val imageLoader: ImageLoader,
        private val click: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NasaItem) = with(binding) {
            photoTitle.text = item.title
            photoThumbnail.loadImage(imageLoader, item.url)
            root.setOnClickListener {
                click(adapterPosition)
            }
        }
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