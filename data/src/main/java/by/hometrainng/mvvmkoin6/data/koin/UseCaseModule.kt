package by.hometrainng.mvvmkoin6.data.koin

import by.hometrainng.mvvmkoin6.domain.usecase.GetBeerDetailsUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetBeersUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetRandomBeerUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetBeersFromDBUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.InsertBeersToDBUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetBreweriesByDistUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetBreweryByIdUseCase
import by.hometrainng.mvvmkoin6.domain.usecase.GetBreweriesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val useCaseModule = module {
    factoryOf(::GetBeersUseCase)
    factoryOf(::GetBeerDetailsUseCase)
    factoryOf(::GetRandomBeerUseCase)
    factoryOf(::GetBeersFromDBUseCase)
    factoryOf(::InsertBeersToDBUseCase)
    factoryOf(::GetBreweriesByDistUseCase)
    factoryOf(::GetBreweryByIdUseCase)
    factoryOf(::GetBreweriesUseCase)
}