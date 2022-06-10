package by.hometrainng.mvvmkoin6.domain.usecase

import by.hometrainng.mvvmkoin6.domain.model.BeerDetails
import by.hometrainng.mvvmkoin6.domain.repository.BeerRemoteRepository

class GetBeerDetailsUseCase(private val beerRemoteRepository: BeerRemoteRepository) {

    suspend operator fun invoke(id: Int): Result<BeerDetails> {
        return beerRemoteRepository.getBeerDetails(id)
    }
}