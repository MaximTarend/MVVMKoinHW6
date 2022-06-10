package by.hometrainng.mvvmkoin6.data.koin

import by.hometrainng.mvvmkoinhw6.manager.SharedPrefsManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedPrefsModule = module {
    singleOf(::SharedPrefsManager)
}