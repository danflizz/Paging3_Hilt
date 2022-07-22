package com.example.encoratask.data.api

import com.example.encoratask.data.model.CharacterResponse
import com.example.encoratask.data.model.DetailsResponse
import com.example.encoratask.util.NetworkConstant.GET_CHARACTER
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @GET("api/character/{character_id}")
    suspend fun fetchCharacterById(@Path("character_id") characterId: String?): DetailsResponse

    @GET(GET_CHARACTER)
    suspend fun fetchCharacters(): CharacterResponse

    @GET
    suspend fun fetchCharactersByUrl(@Url url: String): CharacterResponse
}