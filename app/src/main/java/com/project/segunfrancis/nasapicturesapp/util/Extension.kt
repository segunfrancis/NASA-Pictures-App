package com.project.segunfrancis.nasapicturesapp.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.nasapicturesapp.R

/**
 * Created by SegunFrancis
 */

fun ImageView.loadImage(imageLoader: ImageLoader, url: String) {
    val request = ImageRequest.Builder(context)
        .target(this)
        .data(url)
        .placeholder(circularProgress(context))
        .error(R.drawable.ic_twotone_error)
        .build()
    imageLoader.enqueue(request)
}

fun circularProgress(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 4.0F
        centerRadius = 20.0F
        setColorSchemeColors(Color.rgb(31, 9, 81), Color.rgb(3, 218, 197))
        start()
    }
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun View.showMessage(message: Message) {
    val snackbar = Snackbar.make(this, message.message, Snackbar.LENGTH_LONG)
    if (message.caution)
        snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.red))
    else
        snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.colorAccent))
    snackbar.show()
}