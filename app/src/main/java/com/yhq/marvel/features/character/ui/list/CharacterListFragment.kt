package com.yhq.marvel.features.character.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yhq.marvel.BuildConfig
import com.yhq.marvel.R
import com.yhq.marvel.core.base.BaseAdapter
import com.yhq.marvel.core.base.BaseFragment
import com.yhq.marvel.core.base.ItemClickListener
import com.yhq.marvel.core.extension.viewLifecycleLazy
import com.yhq.marvel.databinding.FragmentCharacterListBinding
import com.yhq.marvel.features.character.ui.CharacterViewModel
import com.yhq.marvel.features.character.ui.list.adapter.CharacterListVH
import com.yhq.marvel.features.character.ui.list.adapter.LoadingVH
import com.yhq.marvel.features.character.ui.list.model.CharacterItemUIModel
import com.yhq.marvel.features.character.ui.list.model.ItemCharacterList

@Suppress("UNCHECKED_CAST", "ConstantConditionIf")
class CharacterListFragment : BaseFragment<CharacterViewModel>(R.layout.fragment_character_list) {

    private lateinit var characterListAdapter: BaseAdapter<ItemCharacterList>
    private val binding by viewLifecycleLazy { FragmentCharacterListBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle(getString(R.string.character_list_title))
        setPullToRefreshListener()
        setUpRecyclerView(emptyList())
        setupObservers()

        loadData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun loadData() {
        if (viewModel.characterList.value.isNullOrEmpty()) {
            showProgress()
            viewModel.loadData()
        } else {
            viewModel.characterList.value?.let { setUpRecyclerView(it) }
        }
    }

    private fun setupObservers() {
        viewModel.characterList.observe(viewLifecycleOwner, Observer {
            hidePullToRefresh()
            hideProgress()
            characterListAdapter.updateItems(it)
            characterListAdapter.isLoading = false
        })

        viewModel.failure.observe(viewLifecycleOwner, Observer {
            hidePullToRefresh()
            executeFailureActions(it)
        })
    }

    private fun hidePullToRefresh() {
        if (binding.swiperefreshCharacterList.isRefreshing)
            binding.swiperefreshCharacterList.isRefreshing = false
    }

    private fun setUpRecyclerView(characterList: List<ItemCharacterList>) {
        characterListAdapter = object : BaseAdapter<ItemCharacterList>(characterList) {
            override fun getLayoutId(position: Int, obj: ItemCharacterList): Int {
                return when (obj) {
                    is CharacterItemUIModel -> R.layout.item_character
                    else -> R.layout.item_loading
                }
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return when (viewType) {
                    R.layout.item_character -> CharacterListVH(view, onItemClickHandler)
                    else -> LoadingVH(view)
                }
            }
        }

        binding.recyclerCharacters.layoutManager = LinearLayoutManager(context)
        binding.recyclerCharacters.adapter = characterListAdapter
        if (BuildConfig.FLAVOR != "mock") {
            binding.recyclerCharacters.addOnScrollListener(characterListAdapter.setupScrollListener { viewModel.loadData() })
        }
    }

    private fun setPullToRefreshListener() {
        binding.swiperefreshCharacterList.setOnRefreshListener {
            viewModel.loadData(true)
        }
    }

    private val onItemClickHandler = object : ItemClickListener<ItemCharacterList> {
        override fun onItemClickListener(
            data: ItemCharacterList,
            positionInAdapter: Int
        ) {
            if (positionInAdapter != -1 && data is CharacterItemUIModel) {
                navigateToCharacterDetail(data)
            }
        }
    }

    private fun navigateToCharacterDetail(characterUIModel: CharacterItemUIModel) {
        viewModel.selectedCharacterUIModel = characterUIModel
        findNavController().navigate(R.id.action_characterListFragment_to_characterDetailFragment)
    }
}
