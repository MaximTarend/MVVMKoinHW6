package by.hometrainng.mvvmkoinhw6.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.hometrainng.mvvmkoin6.data.map.LocationService
import by.hometrainng.mvvmkoin6.domain.usecase.GetBreweriesByDistUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

@SuppressLint("MissingPermission")
class BreweryMapViewModel(
    private val locationService: LocationService,
    private val getBreweriesByDistUseCase: GetBreweriesByDistUseCase
) : ViewModel() {

    private var currentPage = 1

    private val dataFlow = MutableSharedFlow<Unit>(
        replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val locationFlow = flow {
        val service = locationService
        emit(service)
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        replay = 1
    )

    val loadBreweriesByDistFlow = dataFlow
        .map {
            val location = locationService.getCurrentLocation()
            "${location.latitude},${location.longitude}"
        }
        .map {
            getBreweriesByDistUseCase(it, currentPage, PAGE_SIZE)
                .fold(
                    onSuccess = { it },
                    onFailure = { emptyList() }
                )
        }
        .onEach {
            currentPage++
        }
        .runningReduce { accumulator, value -> accumulator + value }

    init {
        onLoadMore()
    }

    fun onLoadMore() {
        dataFlow.tryEmit(Unit)
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}