package by.hometrainng.mvvmkoin6.data.model

import com.google.gson.annotations.SerializedName

internal data class BeerDTO(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    @SerializedName("image_url")
    val imageURL: String,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    @SerializedName("brewers_tips")
    val brewersTips: String

)