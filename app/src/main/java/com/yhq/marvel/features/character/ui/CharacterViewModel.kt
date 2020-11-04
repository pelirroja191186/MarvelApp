package com.yhq.marvel.features.character.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yhq.marvel.core.base.BaseViewModel
import com.yhq.marvel.data.domain.model.character.CharacterDataModel
import com.yhq.marvel.data.domain.usecase.CharacterUseCase
import com.yhq.marvel.data.domain.usecase.CharacterUseCase.CharacterParam
import com.yhq.marvel.features.character.ui.list.model.CharacterItemUIModel
import com.yhq.marvel.features.character.ui.list.model.ItemCharacterList
import com.yhq.marvel.features.character.ui.list.model.LoadingUIModel
import com.yhq.marvel.features.character.ui.list.model.toCharacterUIModel
import javax.inject.Inject

open class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) : BaseViewModel() {

    private var _characterList: MutableLiveData<MutableList<ItemCharacterList>> = MutableLiveData()

    val characterList: LiveData<MutableList<ItemCharacterList>>
        get() = _characterList

    var selectedCharacterUIModel: CharacterItemUIModel? = null

    private fun sizeOfCharacterList() = _characterList.value?.size ?: 0

    fun loadData(isPullToRefresh: Boolean = false) {

        if (isPullToRefresh)
            _characterList.value?.clear()
        else if (sizeOfCharacterList() > 0)
            addLoading()

        characterUseCase(CharacterParam(_characterList.value?.size ?: 0)) {
            it.fold(
                ::handleFailure,
                ::handleCharacterDataResult
            )
        }
    }

     fun handleCharacterDataResult(characterData: CharacterDataModel) {

        val newCharacterList = characterData.results.map { it.toCharacterUIModel() } as MutableList<ItemCharacterList>

        if (sizeOfCharacterList() > 0) {
            removeLoading()
            _characterList.value?.addAll(newCharacterList)
            _characterList.postValue(_characterList.value)
        } else _characterList.postValue(newCharacterList)
    }

    private fun addLoading() = _characterList.value?.add(LoadingUIModel)

    private fun removeLoading() {
        var index = 0
        while (index < sizeOfCharacterList()) {
            if (_characterList.value?.get(index) is LoadingUIModel) {
                _characterList.value?.removeAt(index)
            } else {
                index++
            }
        }
    }
}
