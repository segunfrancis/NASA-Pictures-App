package com.project.segunfrancis.nasapicturesapp.util

import android.graphics.Color
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import com.project.segunfrancis.nasapicturesapp.R

/**
 * Created by SegunFrancis
 */

fun ImageView.loadImage(imageLoader: ImageLoader, url: String) {
    val request = ImageRequest.Builder(context)
        .target(this)
        .data(url)
        .placeholder(this.circularProgress())
        .error(R.drawable.ic_twotone_error)
        .build()
    imageLoader.enqueue(request)
}

fun ImageView.circularProgress(): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 4.0F
        centerRadius = 20.0F
        setColorSchemeColors(Color.rgb(31, 9, 81), Color.rgb(3, 218, 197))
        start()
    }
}