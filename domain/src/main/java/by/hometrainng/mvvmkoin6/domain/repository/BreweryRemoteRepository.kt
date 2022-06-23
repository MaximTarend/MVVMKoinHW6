package by.hometrainng.mvvmkoin6.domain.repository

import by.hometrainng.mvvmkoin6.domain.model.Brewery

interface BreweryRemoteRepository {

    suspend fun getBreweries(page: Int, perPage: Int): Result<List<Brewery>>

    suspend fun getBreweryById(id : String) : Result<Brewery>

    suspend fun getBreweriesByDist(location : String, page: Int, perPage: Int) : Result<List<Brewery>>
}