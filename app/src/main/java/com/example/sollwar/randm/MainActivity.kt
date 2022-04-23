package com.example.sollwar.randm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sollwar.randm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
}