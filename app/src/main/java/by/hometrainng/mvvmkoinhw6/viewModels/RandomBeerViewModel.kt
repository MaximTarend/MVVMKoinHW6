package by.hometrainng.mvvmkoinhw6.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.hometrainng.mvvmkoinhw6.model.Beer
import by.hometrainng.mvvmkoinhw6.model.LceState
import by.hometrainng.mvvmkoinhw6.repository.BeerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RandomBeerViewModel(
    private val beerRepository: BeerRepository
) : ViewModel() {

    val loadRandomFlow = flow {
        val state = beerRepository.getRandomBeer()
            .fold(
                onSuccess = { LceState.Content(it) },
                onFailure = { LceState.Error(it) }
            )
        emit(state)
    }
}