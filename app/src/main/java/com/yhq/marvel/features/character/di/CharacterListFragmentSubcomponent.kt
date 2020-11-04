package com.yhq.marvel.features.character.di

import com.yhq.marvel.features.character.ui.list.CharacterListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface CharacterListFragmentSubcomponent : AndroidInjector<CharacterListFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<CharacterListFragment>
}
