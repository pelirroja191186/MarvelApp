@file:Suppress("unused")

package com.yhq.marvel.core.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
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
        .placeholder(resources.getDrawable(R.drawable.ic_no_image))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)!!
