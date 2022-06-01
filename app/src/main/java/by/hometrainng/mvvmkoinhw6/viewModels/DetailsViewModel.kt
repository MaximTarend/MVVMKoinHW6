package by.hometrainng.mvvmkoinhw6.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.hometrainng.mvvmkoinhw6.model.Beer
import by.hometrainng.mvvmkoinhw6.model.LceState
import by.hometrainng.mvvmkoinhw6.repository.BeerRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class DetailsViewModel(
    private val beerID: Int,
    private val beerRepository: BeerRepository
): ViewModel() {

    private val loadDetailsFlow = flow {
        val state = beerRepository.getBeerDetails(beerID)
            .fold(
                onSuccess = { LceState.Content(it) },
                onFailure = { LceState.Error(it) }
            )
        emit(state)
    }

    val dataFlow = loadDetailsFlow
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            replay = 1
        )
}