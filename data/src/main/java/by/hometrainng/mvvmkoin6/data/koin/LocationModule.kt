package by.hometrainng.mvvmkoinhw6.koin

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import by.hometrainng.mvvmkoin6.data.map.LocationService

val locationModule = module {
    singleOf(::LocationService)
}
