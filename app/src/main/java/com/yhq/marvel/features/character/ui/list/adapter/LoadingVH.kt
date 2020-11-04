package com.yhq.marvel.features.character.ui.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yhq.marvel.core.base.BaseAdapter
import com.yhq.marvel.core.extension.visible
import com.yhq.marvel.features.character.ui.list.model.ItemCharacterList
import kotlinx.android.synthetic.main.layout_progress.view.rlProgressBar

class LoadingVH(
    itemView: View
) : RecyclerView.ViewHolder(itemView),
    BaseAdapter.Binder<ItemCharacterList> {

    override fun bind(item: ItemCharacterList, position: Int) = with(itemView) {
        rlProgressBar.visible()
    }
}
