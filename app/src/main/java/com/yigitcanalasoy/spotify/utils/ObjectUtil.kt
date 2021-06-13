package com.yigitcanalasoy.spotify.utils

import com.google.gson.Gson
import com.yigitcanalasoy.spotify.data.model.Muzikler

class ObjectUtil {

    fun muzikArrayToJsonString(muzikListe: List<Muzikler>?): String? {
        val gson = Gson()
        return gson.toJson(muzikListe)
    }

    fun jsonStringToMuzikArray(jsonString: String?): List<Muzikler>? {
        val gson = Gson()
        return gson.fromJson(jsonString, Array<Muzikler>::class.java).toList()
    }

    fun muzikToJsonString(muzik: Muzikler?): String? {
        val gson = Gson()
        return gson.toJson(muzik)
    }

    fun jsonStringToMuzik(jsonString: String?): Muzikler? {
        val gson = Gson()
        return gson.fromJson(jsonString, Muzikler::class.java)
    }




}