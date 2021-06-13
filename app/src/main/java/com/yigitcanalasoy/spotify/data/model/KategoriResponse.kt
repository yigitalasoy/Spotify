package com.yigitcanalasoy.spotify.data.model
import com.google.gson.annotations.SerializedName


data class KategoriResponseItem(
        @SerializedName("kategoriAd")
    val kategoriAd: String?,
        @SerializedName("kategoriResim")
    val kategoriResim: String?,
        @SerializedName("muzikler")
    val muzikler: List<Muzikler>?
)

data class Muzikler(
        @SerializedName("muzikAdi")
    val muzikAdi: String?,
    @SerializedName("muzikDetay")
    val muzikDetay: MuzikDetay?,
        @SerializedName("resim")
    val resim: String?,
        @SerializedName("sarkici")
    val sarkici: String?
)

data class MuzikDetay(
        @SerializedName("cikisYili")
    val cikisYili: String?,
        @SerializedName("dinlenmeSayisi")
    val dinlenmeSayisi: String?,
        @SerializedName("süresi")
    val süresi: String?
)