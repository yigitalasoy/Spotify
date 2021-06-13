package com.yigitcanalasoy.spotify.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil() {

    fun networkControl(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isAvailable && connectivityManager.activeNetworkInfo!!.isConnected

    }

}