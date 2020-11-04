package com.yhq.marvel.di

import com.yhq.marvel.MarvelApp
import com.yhq.marvel.data.di.DataModule
import com.yhq.marvel.features.character.di.CharacterModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        AndroidModule::class,
        CharacterModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    fun inject(application: MarvelApp)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: MarvelApp): Builder
    }
}
