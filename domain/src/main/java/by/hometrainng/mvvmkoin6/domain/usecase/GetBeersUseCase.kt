package by.hometrainng.mvvmkoin6.domain.usecase

import by.hometrainng.mvvmkoin6.domain.model.Beer
import by.hometrainng.mvvmkoin6.domain.repository.BeerRemoteRepository

class GetBeersUseCase(private val beerRemoteRepository: BeerRemoteRepository) {

    suspend operator fun invoke(page: Int, perPage: Int): Result<List<Beer>> {
        return beerRemoteRepository.getAllBeers(page, perPage)
    }
}