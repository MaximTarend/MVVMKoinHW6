package by.hometrainng.mvvmkoin6.data.repository

import by.hometrainng.mvvmkoin6.data.api.BeerApi
import by.hometrainng.mvvmkoin6.data.mapper.toDetailsDomainModel
import by.hometrainng.mvvmkoin6.data.mapper.toDomainModels
import by.hometrainng.mvvmkoin6.domain.model.Beer
import by.hometrainng.mvvmkoin6.domain.model.BeerDetails
import by.hometrainng.mvvmkoin6.domain.repository.BeerRemoteRepository

internal class BeerRemoteRepositoryImpl(private val beerApi: BeerApi) : BeerRemoteRepository {
    override suspend fun getAllBeers(page: Int, perPage: Int): Result<List<Beer>> {
        return runCatching {
            beerApi.getAllBeers(page, perPage)
        }.map { it.toDomainModels() }
    }

    override suspend fun getBeerDetails(id: Int): Result<BeerDetails> {
        return runCatching {
            beerApi.getBeerById(id)
        }.map { it.last().toDetailsDomainModel() }
    }

    override suspend fun getRandomBeer(): Result<BeerDetails> {
        return runCatching {
            beerApi.getRandomBeer()
        }.map { it.last().toDetailsDomainModel() }
    }
}