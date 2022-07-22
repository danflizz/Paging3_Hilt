package com.example.encoratask.data.api

import androidx.paging.PagingSource
import com.example.encoratask.data.repository.CharacterPaging
import com.example.encoratask.data.model.DetailsResponse
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun getCharacter(): PagingSource<String, CharacterPaging>
    fun getCharacterById(id: String): Flow<DetailsResponse>
}