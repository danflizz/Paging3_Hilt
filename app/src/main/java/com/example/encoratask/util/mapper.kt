package com.example.encoratask.util

import com.example.encoratask.data.model.DetailsResponse
import com.example.encoratask.data.repository.CharacterPaging

fun DetailsResponse.mapToPaging(): CharacterPaging = CharacterPaging(
    id = id.orEmpty(),
    name = name.orEmpty(),
    status = status.orEmpty()
)