package by.hometrainng.mvvmkoinhw6.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.hometrainng.mvvmkoin6.domain.usecase.GetBeerDetailsUseCase
import by.hometrainng.mvvmkoinhw6.model.LceState
import kotlinx.coroutines.flow.*

class DetailsViewModel(
    private val beerID: Int,
    private val getBeerDetailsUseCase: GetBeerDetailsUseCase
): ViewModel() {

     val loadDetailsFlow = flow {
        val state = getBeerDetailsUseCase(beerID)
            .fold(
                onSuccess = { LceState.Content(it) },
                onFailure = { LceState.Error(it) }
            )
        emit(state)
    }        .shareIn(
         scope = viewModelScope,
         started = SharingStarted.Eagerly,
         replay = 1
     )
}