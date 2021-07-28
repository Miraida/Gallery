package com.geek.gallery.exten

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri: String) {
    Glide.with(this)
        .load(uri)
        .into(this)
}
fun View.visible(){
    visibility = View.VISIBLE
}
fun View.gone(){
    visibility = View.GONE
}