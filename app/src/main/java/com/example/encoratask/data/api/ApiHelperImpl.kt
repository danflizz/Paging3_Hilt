package com.example.encoratask.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.encoratask.data.model.DetailsResponse
import com.example.encoratask.data.repository.CharacterPaging
import com.example.encoratask.util.NetworkConstant.EMPTY_DATA
import com.example.encoratask.util.NetworkConstant.FIRST_PAGE
import com.example.encoratask.util.mapToPaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    private var previousPage: String? = null
    private var nextPage: String? = null

    override fun getCharacter(): PagingSource<String, CharacterPaging> {
        return object : PagingSource<String, CharacterPaging>() {

            override suspend fun load(params: LoadParams<String>): LoadResult<String, CharacterPaging> {
                val page = params.key ?: FIRST_PAGE

                return try {
                    val response = if (page == FIRST_PAGE) {
                        apiService.fetchCharacters()
                    } else {
                        apiService.fetchCharactersByUrl(nextPage.orEmpty())
                    }
                    response.info?.let {
                        previousPage = response.info.prev
                        nextPage = response.info.next
                    }
                    val data = response.results?.map {
                        it.mapToPaging()
                    }

                    if (data.isNullOrEmpty()) {
                        LoadResult.Error(Throwable(EMPTY_DATA))
                    } else {
                        LoadResult.Page(data.orEmpty(), previousPage, nextPage)
                    }
                } catch (exception: IOException) {
                    LoadResult.Error(exception)
                } catch (exception: HttpException) {
                    LoadResult.Error(exception)
                }
            }

            override fun getRefreshKey(state: PagingState<String, CharacterPaging>): String? = null

        }
    }

    override fun getCharacterById(id: String): Flow<DetailsResponse> = flow {
        emit(apiService.fetchCharacterById(id))
    }

}