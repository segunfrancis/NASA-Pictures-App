package com.project.segunfrancis.nasapicturesapp.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.segunfrancis.nasapicturesapp.model.NasaItem

/**
 * Created by SegunFrancis
 *
 * [DiffUtil] class for [ListAdapter]s
 */

class PictureDiffUtil : DiffUtil.ItemCallback<NasaItem>() {

    override fun areItemsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
        return oldItem.explanation == newItem.explanation
    }


    override fun areContentsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
        return oldItem == newItem
    }

}