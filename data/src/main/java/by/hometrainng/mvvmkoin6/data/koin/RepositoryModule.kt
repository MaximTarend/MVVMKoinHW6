package by.hometrainng.mvvmkoin6.data.koin

import by.hometrainng.mvvmkoin6.data.database.BeerDao
import by.hometrainng.mvvmkoin6.data.repository.BeerLocalRepositoryImpl
import by.hometrainng.mvvmkoin6.data.repository.BeerRemoteRepositoryImpl
import by.hometrainng.mvvmkoin6.domain.repository.BeerLocalRepository
import by.hometrainng.mvvmkoin6.domain.repository.BeerRemoteRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

import org.koin.dsl.module

internal val repositoryModule = module {

    singleOf(::BeerRemoteRepositoryImpl) { bind<BeerRemoteRepository>() }

//    single { BeerLocalRepositoryImpl(get()) }.bind<BeerLocalRepository>()
    singleOf(::BeerLocalRepositoryImpl) {  bind<BeerLocalRepository>() }
}
