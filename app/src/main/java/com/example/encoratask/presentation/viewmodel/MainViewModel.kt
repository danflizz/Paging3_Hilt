package com.example.encoratask.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.encoratask.data.repository.CharacterRepository

class MainViewModel @ViewModelInject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val characters = characterRepository.getCharacters().cachedIn(viewModelScope)

    fun fetchCharacterById(id: String) = characterRepository.fetchCharactersById(id)

}