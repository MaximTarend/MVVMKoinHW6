package by.hometrainng.mvvmkoin6.data.koin

import org.koin.dsl.module

val dataModule = module {
    includes(
        databaseModule,
        repositoryModule,
        networkModule,
        useCaseModule
    )
}