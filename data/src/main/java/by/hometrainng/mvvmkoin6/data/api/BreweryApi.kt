package by.hometrainng.mvvmkoin6.data.api

import by.hometrainng.mvvmkoin6.data.model.BreweryDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface BreweryApi {

    @GET("breweries")
    suspend fun getBreweries(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<BreweryDTO>

    @GET("breweries/{id}")
    suspend fun getBreweryById(
        @Path("id") id : String
    ) : BreweryDTO

    @GET("breweries")
    suspend fun getBreweriesByDist(
        @Query("by_dist") location : String, // 45.00,45.00
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : List<BreweryDTO>
}

