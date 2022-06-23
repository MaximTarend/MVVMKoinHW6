package by.hometrainng.mvvmkoin6.domain.usecase

import by.hometrainng.mvvmkoin6.domain.model.Brewery
import by.hometrainng.mvvmkoin6.domain.repository.BreweryRemoteRepository

class GetBreweriesByDistUseCase(private val breweryRemoteRepository: BreweryRemoteRepository) {

    suspend operator fun invoke(location: String, page: Int, perPage: Int): Result<List<Brewery>> {
        return breweryRemoteRepository.getBreweriesByDist(location, page, perPage)
    }
}