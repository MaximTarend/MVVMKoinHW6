package by.hometrainng.mvvmkoinhw6.koin

import by.hometrainng.mvvmkoinhw6.repository.BeerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        BeerRepository(get())
    }
}