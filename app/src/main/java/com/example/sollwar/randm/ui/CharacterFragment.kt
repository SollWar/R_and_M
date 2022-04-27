package com.example.sollwar.randm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sollwar.randm.MainViewModel
import com.example.sollwar.randm.R
import com.example.sollwar.randm.data.model.Result
import com.example.sollwar.randm.databinding.FragmentCharacterBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharacterFragment : Fragment() {
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val vm by sharedViewModel<MainViewModel>()
    private lateinit var character: Result

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        character = vm.character!!

        binding.name.text = character.name
        binding.toolbar.title = character.name
        binding.species.text = getString(R.string.species, character.species)
        binding.gender.text = getString(R.string.gender, character.gender)
        binding.status.text = getString(R.string.status, character.status)
        binding.episodes.text = getString(R.string.episodes, character.episode.size.toString())
        binding.locationName.text = getString(R.string.location_name, character.location.name)
        Picasso.get()
            .load(character.image)
            .placeholder(R.drawable.big_placeholder)
            .into(binding.avatar)

        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(): CharacterFragment {
            return CharacterFragment()
        }
    }
}