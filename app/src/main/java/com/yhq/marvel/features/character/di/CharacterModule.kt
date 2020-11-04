package com.yhq.marvel.features.character.di

import androidx.lifecycle.ViewModel
import com.yhq.marvel.di.ViewModelKey
import com.yhq.marvel.features.character.ui.CharacterViewModel
import com.yhq.marvel.features.character.ui.detail.CharacterDetailFragment
import com.yhq.marvel.features.character.ui.list.CharacterListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(subcomponents = [CharacterDetailFragmentSubcomponent::class, CharacterListFragmentSubcomponent::class])
abstract class CharacterModule {

    @Binds
    @IntoMap
    @ClassKey(CharacterDetailFragment::class)
    internal abstract fun bindCharacterDetailFragmentInjectorFactory(factory: CharacterDetailFragmentSubcomponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(CharacterListFragment::class)
    internal abstract fun bindCharacterListFragmentInjectorFactory(factory: CharacterListFragmentSubcomponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(CharacterViewModel::class)
    abstract fun bindCharacterViewModel(viewModel: CharacterViewModel): ViewModel
}
