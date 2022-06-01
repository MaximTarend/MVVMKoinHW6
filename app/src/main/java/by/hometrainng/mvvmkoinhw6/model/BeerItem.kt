package by.hometrainng.mvvmkoinhw6.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class BeerItem(
    val id: Int,
    val name: String,
    val imageURL: String
)