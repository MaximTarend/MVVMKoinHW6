package by.hometrainng.mvvmkoin6.data.koin

import by.hometrainng.mvvmkoin6.data.repository.BeerRemoteRepositoryImpl
import by.hometrainng.mvvmkoin6.domain.repository.BeerRemoteRepository
import by.hometrainng.mvvmkoin6.domain.usecase.GetBeerDetailsUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetBeersUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetRandomBeerUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetBeersFromDBUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.InsertBeersToDBUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val useCaseModule = module {
    factoryOf(::GetBeersUseCase)
    factoryOf(::GetBeerDetailsUseCase)
    factoryOf(::GetRandomBeerUseCase)
    factoryOf(::GetBeersFromDBUseCase)
    factoryOf(::InsertBeersToDBUseCase)
}