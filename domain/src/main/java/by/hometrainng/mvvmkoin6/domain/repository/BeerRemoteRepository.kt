package by.hometrainng.mvvmkoin6.domain.repository

import by.hometrainng.mvvmkoin6.domain.model.Beer
import by.hometrainng.mvvmkoin6.domain.model.BeerDetails

interface BeerRemoteRepository {

    suspend fun getAllBeers(page: Int, perPage: Int): Result<List<Beer>>

    suspend fun getBeerDetails(id: Int): Result<BeerDetails>

    suspend fun getRandomBeer(): Result<BeerDetails>
}