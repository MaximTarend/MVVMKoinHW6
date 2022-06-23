package by.hometrainng.mvvmkoin6.domain.usecase

import by.hometrainng.mvvmkoin6.domain.model.Brewery
import by.hometrainng.mvvmkoin6.domain.repository.BreweryRemoteRepository

class GetBreweryByIdUseCase(private val breweryRemoteRepository: BreweryRemoteRepository) {

    suspend operator fun invoke(id: String): Result<Brewery> {
        return breweryRemoteRepository.getBreweryById(id)
    }
}