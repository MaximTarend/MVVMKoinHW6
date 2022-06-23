package by.hometrainng.mvvmkoin6.domain.usecase

import by.hometrainng.mvvmkoin6.domain.model.Brewery
import by.hometrainng.mvvmkoin6.domain.repository.BreweryRemoteRepository

class GetBreweriesUseCase(private val breweryRemoteRepository: BreweryRemoteRepository) {

    suspend operator fun invoke(page: Int, perPage: Int): Result<List<Brewery>> {
        return breweryRemoteRepository.getBreweries(page, perPage)
    }
}