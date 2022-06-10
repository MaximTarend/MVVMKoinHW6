package by.hometrainng.mvvmkoin6.domain.usecase

import by.hometrainng.mvvmkoin6.domain.model.Beer
import by.hometrainng.mvvmkoin6.domain.repository.BeerLocalRepository

class InsertBeersToDBUseCase(private val beerLocalRepository: BeerLocalRepository) {

    suspend operator fun invoke(beers: List<Beer>) {
        beerLocalRepository.insertBeersToDB(beers)
    }
}