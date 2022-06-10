package by.hometrainng.mvvmkoin6.domain.usecase

import by.hometrainng.mvvmkoin6.domain.model.BeerDetails
import by.hometrainng.mvvmkoin6.domain.repository.BeerRemoteRepository

class GetRandomBeerUseCase(private val beerRemoteRepository: BeerRemoteRepository) {

    suspend operator fun invoke(): Result<BeerDetails> {
        return beerRemoteRepository.getRandomBeer()
    }
}