package by.hometrainng.mvvmkoin6.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
//    val description: String,
    @ColumnInfo(name = "image_url")
    val imageURL: String
)