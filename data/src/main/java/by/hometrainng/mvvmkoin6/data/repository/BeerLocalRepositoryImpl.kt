package by.hometrainng.mvvmkoin6.data.repository

import by.hometrainng.mvvmkoin6.data.database.BeerDao
import by.hometrainng.mvvmkoin6.data.mapper.toBeerEntity
import by.hometrainng.mvvmkoin6.data.mapper.toDomainModel
import by.hometrainng.mvvmkoin6.domain.model.Beer
import by.hometrainng.mvvmkoin6.domain.model.BeerDetails
import by.hometrainng.mvvmkoin6.domain.repository.BeerLocalRepository
import by.hometrainng.mvvmkoin6.domain.repository.BeerRemoteRepository

internal class BeerLocalRepositoryImpl(
    private val beerDao: BeerDao
): BeerLocalRepository {
    override suspend fun getBeersFromBD(): List<Beer> {
        return beerDao.getBeers().map {
                it.toDomainModel()
            }
    }

    override suspend fun insertBeersToDB(beers: List<Beer>) {
        beerDao.insertBeers(
            beers.map {
                it.toBeerEntity()
            }
        )
    }


}