package by.hometrainng.mvvmkoinhw6.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import by.hometrainng.mvvmkoinhw6.model.Beer

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer")
    suspend fun getBeers(): List<Beer>

    @Insert(onConflict = REPLACE)
    suspend fun insertBeers(beers: List<Beer>)
}