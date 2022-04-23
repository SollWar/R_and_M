package com.example.sollwar.randm.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sollwar.randm.data.model.CharacterList
import com.example.sollwar.randm.data.model.Result

typealias CharacterPageLoader = suspend (page: Int) -> CharacterList

class CharacterPagingSource(
    private val loader: CharacterPageLoader
) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        Log.d("pageIndex", params.key.toString())
        val pageIndex = params.key ?: 1

        return try {
            val charactersList = loader.invoke(pageIndex)
            return LoadResult.Page(
                data = charactersList.results,
                prevKey = if (charactersList.info.prev == null) null else pageIndex - 1,
                nextKey = if (charactersList.info.next == null) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}