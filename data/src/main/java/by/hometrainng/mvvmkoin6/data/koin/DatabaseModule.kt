package by.hometrainng.mvvmkoin6.data.koin

import androidx.room.Room
import by.hometrainng.mvvmkoin6.data.database.AppDatabase
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database.db"
        ).build()
    }

    single { get<AppDatabase>().beerDao() }
}