package com.yhq.marvel.features.character.ui.detail

import android.os.Bundle
import android.view.View
import com.yhq.marvel.R
import com.yhq.marvel.core.base.BaseFragment
import com.yhq.marvel.core.extension.loadFromUrl
import com.yhq.marvel.core.extension.viewLifecycleLazy
import com.yhq.marvel.databinding.FragmentCharacterDetailBinding
import com.yhq.marvel.features.character.ui.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_detail_content.description
import kotlinx.android.synthetic.main.fragment_character_detail_content.name

class CharacterDetailFragment : BaseFragment<CharacterViewModel>(R.layout.fragment_character_detail) {

    private val binding by viewLifecycleLazy { FragmentCharacterDetailBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle(getString(R.string.character_detail_title))
        setUpUI()
    }

    private fun setUpUI() {
        viewModel.selectedCharacterUIModel?.let { selectCharacter ->
            binding.apply {
                imageDetail.loadFromUrl(selectCharacter.imageURL)
                name.text = selectCharacter.name
                description.text = selectCharacter.description
            }
        }
    }
}



