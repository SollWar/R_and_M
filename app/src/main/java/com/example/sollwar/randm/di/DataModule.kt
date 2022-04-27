package com.example.sollwar.randm.di

import com.example.sollwar.randm.data.RickAndMortyRep
import com.example.sollwar.randm.data.retrofit.RickAndMortyApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        RickAndMortyRep(rickAndMortyApi = get())
    }

    single<RickAndMortyApi> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val rickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)
        rickAndMortyApi
    }
}