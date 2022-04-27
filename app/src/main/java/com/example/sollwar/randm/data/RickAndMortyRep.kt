package com.example.sollwar.randm.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sollwar.randm.data.model.CharacterList
import com.example.sollwar.randm.data.model.Result
import com.example.sollwar.randm.data.retrofit.RickAndMortyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

class RickAndMortyRep(
    private val rickAndMortyApi: RickAndMortyApi
) {

    fun getPageCharacters(): Flow<PagingData<Result>> {
        val loader: CharacterPageLoader = { page ->
            getCharacters(page)
        }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { CharacterPagingSource(loader) }
        ).flow
    }

    private suspend fun getCharacters(page: Int): CharacterList = withContext(Dispatchers.IO) {
        val request = rickAndMortyApi.getCharacterList(page)
        return@withContext request.body()!!
    }
}