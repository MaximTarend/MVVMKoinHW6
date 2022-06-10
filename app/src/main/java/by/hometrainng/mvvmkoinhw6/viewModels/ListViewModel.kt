package by.hometrainng.mvvmkoinhw6.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.hometrainng.mvvmkoin6.domain.usecase.GetBeersFromDBUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetBeersUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.InsertBeersToDBUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class ListViewModel(
    private val getBeersUseCase: GetBeersUseCase,
    private val getBeersFromDBUseCase: GetBeersFromDBUseCase,
    private val insertBeersToDBUseCase: InsertBeersToDBUseCase
) : ViewModel() {

    private var isLoading = false
    private var currentPage = 1

    private val loadMoreFlow = MutableSharedFlow<Unit>(
        replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    init {
        onLoadMore()
    }

    val dataFlow = loadMoreFlow
        .filter { !isLoading }
        .onEach { isLoading = true }
        .map {
            getBeersUseCase(currentPage, PAGE_SIZE)
                .fold(
                    onSuccess = { it },
                    onFailure = { error("Upload Failure") }
                )
        }
        .onEach {
            insertBeersToDBUseCase(it)
            isLoading = false
            currentPage++
        }
        .runningReduce { accumulator, value -> accumulator + value }
        .onStart { emit(getBeersFromDBUseCase()) }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            replay = 1
        )

    fun onLoadMore() {
        loadMoreFlow.tryEmit(Unit)
    }

    companion object {
        private const val PAGE_SIZE = 25
    }
}