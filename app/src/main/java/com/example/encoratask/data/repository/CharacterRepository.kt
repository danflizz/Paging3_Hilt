package com.example.encoratask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.encoratask.data.api.ApiHelper
import com.example.encoratask.util.NetworkConstant.NETWORK_PAGE_SIZE
import com.example.encoratask.data.model.DetailsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    fun getCharacters(): Flow<PagingData<CharacterPaging>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 4
            ),
            pagingSourceFactory = {
                apiHelper.getCharacter()
            }).flow

    fun fetchCharactersById(id: String): Flow<DetailsResponse> = apiHelper.getCharacterById(id)
}