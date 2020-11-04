package com.yhq.marvel.features.character.ui.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yhq.marvel.core.base.BaseAdapter
import com.yhq.marvel.core.base.ItemClickListener
import com.yhq.marvel.core.extension.loadFromUrl
import com.yhq.marvel.databinding.ItemCharacterBinding
import com.yhq.marvel.features.character.ui.list.model.CharacterItemUIModel
import com.yhq.marvel.features.character.ui.list.model.ItemCharacterList

class CharacterListVH(
    itemView: View,
    private val onItemClick: ItemClickListener<ItemCharacterList>
) : RecyclerView.ViewHolder(itemView),
    BaseAdapter.Binder<CharacterItemUIModel> {

    override fun bind(item: CharacterItemUIModel, position: Int) = with(itemView) {

        ItemCharacterBinding.bind(itemView).apply {
            name.text = item.name
            image.loadFromUrl(item.imageURL)
        }


        setOnClickListener { onItemClick.onItemClickListener(item, position) }

    }
}
