package by.hometrainng.mvvmkoinhw6.room

import androidx.room.Database
import androidx.room.RoomDatabase
import by.hometrainng.mvvmkoinhw6.model.Beer

@Database(entities = [Beer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao
}