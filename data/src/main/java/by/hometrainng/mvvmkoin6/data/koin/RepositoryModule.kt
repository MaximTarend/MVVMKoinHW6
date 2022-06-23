package by.hometrainng.mvvmkoin6.data.koin

import by.hometrainng.mvvmkoin6.data.api.BeerApi
import by.hometrainng.mvvmkoin6.data.repository.BeerLocalRepositoryImpl
import by.hometrainng.mvvmkoin6.data.repository.BeerRemoteRepositoryImpl
import by.hometrainng.mvvmkoin6.data.repository.BreweryRemoteRepositoryImpl
import by.hometrainng.mvvmkoin6.domain.repository.BeerLocalRepository
import by.hometrainng.mvvmkoin6.domain.repository.BeerRemoteRepository
import by.hometrainng.mvvmkoin6.domain.repository.BreweryRemoteRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf

import org.koin.dsl.module

internal val repositoryModule = module {

    singleOf(::BeerRemoteRepositoryImpl) {
        bind<BeerRemoteRepository>()
/*        named("beer")*/
    }

//    single { BeerLocalRepositoryImpl(get()) }.bind<BeerLocalRepository>()
    singleOf(::BeerLocalRepositoryImpl) {  bind<BeerLocalRepository>() }

    singleOf(::BreweryRemoteRepositoryImpl) {
        bind<BreweryRemoteRepository>()
/*        named("brewery")*/
    }
}
