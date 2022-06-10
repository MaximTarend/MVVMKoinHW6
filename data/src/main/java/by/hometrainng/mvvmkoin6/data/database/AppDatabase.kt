package by.hometrainng.mvvmkoin6.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.hometrainng.mvvmkoin6.data.model.BeerEntity

@Database(entities = [BeerEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao
}