package com.example.encoratask.data.model

import java.io.Serializable

data class DetailsResponse(
    val id: String? = "",
    val name: String? = "",
    val status: String? = "",
    val species: String? = "",
    val type: String? = "",
    val gender: String? = "",
    val image: String? = ""
) : Serializable