package com.yhq.marvel.features.character.di

import com.yhq.marvel.features.character.ui.detail.CharacterDetailFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface CharacterDetailFragmentSubcomponent : AndroidInjector<CharacterDetailFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<CharacterDetailFragment>
}
