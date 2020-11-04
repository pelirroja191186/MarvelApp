package com.yhq.marvel.core.base

interface ItemClickListener<T> {
    fun onItemClickListener(
        data: T, positionInAdapter: Int = -1
    )
}
