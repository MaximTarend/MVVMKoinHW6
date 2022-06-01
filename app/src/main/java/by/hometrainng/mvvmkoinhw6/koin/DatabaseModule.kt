package by.hometrainng.mvvmkoinhw6.koin

import androidx.room.Room
import by.hometrainng.mvvmkoinhw6.room.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database.db"
        ).build()
    }
}