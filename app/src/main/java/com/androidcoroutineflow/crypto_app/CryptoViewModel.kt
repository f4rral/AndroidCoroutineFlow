package com.androidcoroutineflow.crypto_app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    init {
        viewModelScope.launch {
            repository.loadData()
        }
    }

    val state: Flow<State> = repository.currencyListFlow
        .filter {
            it.isNotEmpty()
        }
        .map {
            State.Content(it) as State
        }
        .onStart {
            emit(State.Loading)
        }

    fun refreshList() {
        viewModelScope.launch {
            repository.loadData()
        }
    }
}