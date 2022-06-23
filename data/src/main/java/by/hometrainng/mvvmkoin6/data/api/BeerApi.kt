package by.hometrainng.mvvmkoin6.data.api

import by.hometrainng.mvvmkoin6.data.model.BeerDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface BeerApi {

    @GET("beers")
    suspend fun getAllBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<BeerDTO>

    @GET("beers/{id}")
    suspend fun getBeerById(
        @Path("id") id : Int
    ) : List<BeerDTO>

    @GET("beers/random")
    suspend fun getRandomBeer() : List<BeerDTO>
}