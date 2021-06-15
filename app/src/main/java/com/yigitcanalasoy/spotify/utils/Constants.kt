package com.yigitcanalasoy.spotify.utils


object Constants {
    val BASE_URL="https://raw.githubusercontent.com/yigitalasoy/Spotify/main/data/"
    const val KATEGORILER_JSON = "kategoriler.json"
    const val KULLANICILAR_JSON = "kullanicilar.json"

    val TIKLANAN_KATEGORI_MUZIKLERI="tiklananKategorininMuzikleri"
    val TIKLANAN_MUZIK="tiklananMuzik"


    val RECYCLERVIEWKATEGORILERSPANCOUNT = 2
    val RECYCLERVIEWMUZIKLERSPANCOUNT = 2

    val A_DAN_Z_YE_SIRALA = 0
    val Z_DEN_A_YA_SIRALA = 1

    val SPLASH_BEKLEME_SURESI = 3000
    val SPLASH_BEKLEME_SURESI_TICK = 1000

    val MUZIK_DETAY_BEKLEME_SURESI : Long = 1000
    val MUZIK_DETAY_SAAT_DAKIKA = 60
    val MUZIK_DETAY_TEXTBOX_DAKIKA_ID = 0
    val MUZIK_DETAY_TEXTBOX_SANIYE_ID = 1
    val MUZIK_DETAY_SEEKBAR_ILERLEME = 1
    val MUZIK_DETAY_DINLENME_TEXTBOX = " dinlenme"


}