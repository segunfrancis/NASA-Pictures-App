package com.project.segunfrancis.nasapicturesapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.ImageLoader
import com.like.LikeButton
import com.project.segunfrancis.nasapicturesapp.R
import com.project.segunfrancis.nasapicturesapp.ui.util.PictureDiffUtil
import com.project.segunfrancis.nasapicturesapp.databinding.ItemPictureBinding
import com.project.segunfrancis.nasapicturesapp.model.NasaItem

/**
 * Created by SegunFrancis
 */

class PictureListAdapter(
    private val imageLoader: ImageLoader,
    private val click: (position: Int) -> Unit,
    private val like: (NasaItem) -> Unit,
    private val unlike: (NasaItem) -> Unit,
    private val lb: (LikeButton, NasaItem) -> Unit
) :
    ListAdapter<NasaItem, PictureListViewHolder>(PictureDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return PictureListViewHolder(ItemPictureBinding.bind(view), imageLoader, click, like, unlike, lb)
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
