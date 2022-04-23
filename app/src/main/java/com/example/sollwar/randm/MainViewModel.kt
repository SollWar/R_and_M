package com.example.sollwar.randm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sollwar.randm.data.RickAndMortyRep
import com.example.sollwar.randm.data.model.Result
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    private val rickAndMortyRep = RickAndMortyRep()

    var character: Result? = null
    val charactersFlow: Flow<PagingData<Result>> = rickAndMortyRep.getPageCharacters().cachedIn(viewModelScope)

}