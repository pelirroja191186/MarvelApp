@file:Suppress("unused")

package com.yhq.marvel.core.extension

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yhq.marvel.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_no_image))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
