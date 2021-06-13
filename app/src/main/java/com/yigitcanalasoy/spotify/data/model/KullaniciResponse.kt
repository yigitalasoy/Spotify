package com.yigitcanalasoy.spotify.data.model
import com.google.gson.annotations.SerializedName

data class KullaniciResponseItem(
    @SerializedName("eMail")
    val eMail: String?,
    @SerializedName("parola")
    val parola: String?
)