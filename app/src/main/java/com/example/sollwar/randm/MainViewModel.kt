package com.example.sollwar.randm

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.sollwar.randm.data.RickAndMortyRep
import com.example.sollwar.randm.data.model.Result
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    private val rickAndMortyRep = RickAndMortyRep()

    val charactersFlow: Flow<PagingData<Result>> = rickAndMortyRep.getPageCharacters()
}