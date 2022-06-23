package by.hometrainng.mvvmkoin6.data.model

import com.google.gson.annotations.SerializedName


internal data class BreweryDTO(
    val id: String,
    val name: String,
    val longitude: String? = "",
    val latitude: String? = "",
    @SerializedName("website_url")
    val websiteUrl: String? = "",
    val phone: String? = ""
)