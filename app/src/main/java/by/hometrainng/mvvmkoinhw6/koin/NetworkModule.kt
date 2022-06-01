package by.hometrainng.mvvmkoinhw6.koin

import by.hometrainng.mvvmkoinhw6.retrofit.BeerApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val networkModule = module {

    single {
        OkHttpClient.Builder().build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(" https://api.punkapi.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create<BeerApi>()
    }
}