package uz.murodjon_sattorov.myfilms.core.model

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profile_path: String?
)
