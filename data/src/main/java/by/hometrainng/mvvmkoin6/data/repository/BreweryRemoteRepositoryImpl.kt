package by.hometrainng.mvvmkoin6.data.repository

import by.hometrainng.mvvmkoin6.data.api.BreweryApi
import by.hometrainng.mvvmkoin6.data.mapper.toDomainModel
import by.hometrainng.mvvmkoin6.data.mapper.toDomainModels
import by.hometrainng.mvvmkoin6.domain.model.Brewery
import by.hometrainng.mvvmkoin6.domain.repository.BreweryRemoteRepository

internal class BreweryRemoteRepositoryImpl(private val breweryApi: BreweryApi) : BreweryRemoteRepository {
    override suspend fun getBreweries(page: Int, perPage: Int): Result<List<Brewery>> {
        return runCatching {
            breweryApi.getBreweries(page, perPage)
        }.map { it.toDomainModels() }
    }

    override suspend fun getBreweryById(id: String): Result<Brewery> {
        return runCatching {
            breweryApi.getBreweryById(id)
        }.map { it.toDomainModel() }
    }

    override suspend fun getBreweriesByDist(
        location: String,
        page: Int,
        perPage: Int
    ): Result<List<Brewery>> {
        return runCatching {
            breweryApi.getBreweriesByDist(location, page, perPage)
        }.map { it.toDomainModels() }
    }
}