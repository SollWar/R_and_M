package com.example.sollwar.randm.di

import com.example.sollwar.randm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(
            rickAndMortyRep = get()
        )
    }
}