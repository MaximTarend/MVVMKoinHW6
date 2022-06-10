package by.hometrainng.mvvmkoin6.domain.repository

import by.hometrainng.mvvmkoin6.domain.model.Beer

interface BeerLocalRepository {

    suspend fun getBeersFromBD() : List<Beer>

    suspend fun insertBeersToDB(beers: List<Beer>)
}