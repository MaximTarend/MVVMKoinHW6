package by.hometrainng.mvvmkoin6.data.koin

import by.hometrainng.mvvmkoinhw6.koin.locationModule
import org.koin.dsl.module

val dataModule = module {
    includes(
        databaseModule,
        repositoryModule,
        networkModule,
        network2Module,
        useCaseModule,
        locationModule
    )
}