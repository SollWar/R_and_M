package com.example.sollwar.randm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sollwar.randm.databinding.ActivityMainBinding
import com.example.sollwar.randm.ui.CharacterFragment
import com.example.sollwar.randm.ui.CharacterListFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, CharacterListFragment.newInstance())
                .commit()
        }
    }

    override fun characterSelect() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, CharacterFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}