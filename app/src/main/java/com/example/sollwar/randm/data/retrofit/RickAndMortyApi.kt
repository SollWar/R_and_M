package com.example.sollwar.randm.data.retrofit

import android.util.Log
import com.example.sollwar.randm.data.model.CharacterList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("/api/character/")
    suspend fun getCharacterList(
        @Query("page") page: Int = 1
    ): Response<CharacterList>

    companion object {
        private var rickAndMortyApi: RickAndMortyApi? = null
        fun getInstance(): RickAndMortyApi {
            if (rickAndMortyApi == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                rickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)
            }
            return rickAndMortyApi!!
        }
    }
}