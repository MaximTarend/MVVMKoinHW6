package by.hometrainng.mvvmkoinhw6.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Beer(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    val description: String,
    @SerializedName("image_url")
    val imageURL: String,

)