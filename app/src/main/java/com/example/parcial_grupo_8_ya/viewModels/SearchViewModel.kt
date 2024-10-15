package com.example.parcial_grupo_8_ya.viewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class SearchViewModel : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }
}