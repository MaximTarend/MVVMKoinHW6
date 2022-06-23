package by.hometrainng.mvvmkoin6.domain.model

data class Brewery(
    val id: String,
    val name: String,
    val longitude: String? = "",
    val latitude: String? = "",
    val websiteUrl: String? = "",
    val phone: String? = ""
)