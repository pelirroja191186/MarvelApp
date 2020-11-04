package com.yhq.marvel.core.base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

@Suppress("unused")
abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var isLoading = false

    private var listItems: List<T>

    private var viewHolderList = mutableListOf<RecyclerView.ViewHolder>()

    constructor(listItems: List<T>) {
        this.listItems = listItems
    }

    constructor() {
        listItems = emptyList()
    }

    fun updateItems(listItems: List<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh = getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
            , viewType
        )
        viewHolderList.add(vh)
        return vh
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItems[position], position)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, listItems[position])
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    fun getViewHolder(position: Int): RecyclerView.ViewHolder {
        return viewHolderList[position]
    }

    internal interface Binder<T> {
        fun bind(item: T, position: Int = -1)
    }

    fun setupScrollListener(loadMore: () -> Unit): OnScrollListener {
        return object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val linearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager?

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() === listItems.size - 1) {
                        isLoading = true
                        Log.d("load more","load more")
                        loadMore()
                    }
                }
            }
        }
    }
}

