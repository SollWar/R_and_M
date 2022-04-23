package com.example.sollwar.randm.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sollwar.randm.MainViewModel
import com.example.sollwar.randm.data.adapters.CharactersAdapter
import com.example.sollwar.randm.data.adapters.DefaultLoadStateAdapter
import com.example.sollwar.randm.data.adapters.TryAgainAction
import com.example.sollwar.randm.data.model.Result
import com.example.sollwar.randm.databinding.FragmentCharacterListBinding
import com.example.sollwar.randm.navigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment(), CharactersAdapter.OnCharacterListener {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var mainLoadStateHolder: DefaultLoadStateAdapter.Holder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        val adapter = CharactersAdapter(this)
        val tryAgainAction: TryAgainAction = {adapter.retry()}
        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)
        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterWithLoadState


        mainLoadStateHolder = DefaultLoadStateAdapter.Holder(
            binding.loadStateView,
            tryAgainAction
        )

        observeCharacters(adapter)
        observeLoadState(adapter)

        return binding.root
    }

    private fun observeCharacters(adapter: CharactersAdapter) {
        lifecycleScope.launch {
            viewModel.charactersFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun observeLoadState(adapter: CharactersAdapter) {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
                mainLoadStateHolder.bind(state.refresh)
            }
        }
    }

    override fun onCharacterClick(result: Result) {
        viewModel.character = result
        navigator().characterSelect()
        Log.d("onCharacterClick", result.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(): CharacterListFragment {
            return CharacterListFragment()
        }
    }


}