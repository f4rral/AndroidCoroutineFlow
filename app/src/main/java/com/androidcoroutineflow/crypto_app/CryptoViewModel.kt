package com.androidcoroutineflow.crypto_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository
    private val loadingFlow = MutableSharedFlow<State>()

    val state: Flow<State> = repository.getCurrencyList()
        .filter {
            it.isNotEmpty()
        }
        .map {
            State.Content(it) as State
        }
        .onStart {
            emit(State.Loading)
        }
        .mergeWith(loadingFlow)
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            replay = 1
        )

    private fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
        return merge(this, another)
    }

    fun refreshList() {
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            repository.refreshList()
        }
    }
}