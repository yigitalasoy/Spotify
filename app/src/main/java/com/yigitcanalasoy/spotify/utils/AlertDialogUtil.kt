package com.yigitcanalasoy.spotify.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.provider.Settings
import kotlin.system.exitProcess

class AlertDialogUtil() {

    fun ortakAlertDialog(istenilenAlert : AlertDialogEnum,activity: Activity, alertTitleText: String, alertMessageText: String, alertPositiveButtonText: String, alertNegativeButtonText: String) {
        AlertDialog.Builder(activity).apply {
            setTitle(alertTitleText)
            setMessage(alertMessageText)
            setPositiveButton(alertPositiveButtonText) { dialogInterface, which ->
                if(istenilenAlert.equals(AlertDialogEnum.INTERNET_KONTROL_ALERT)){
                    activity.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                } else if(istenilenAlert.equals(AlertDialogEnum.CIKIS_YAP_ALERT)){
                    exitProcess(-1)
                }
            }
            setNegativeButton(alertNegativeButtonText) { dialogInterface, which->
                if(istenilenAlert.equals(AlertDialogEnum.INTERNET_KONTROL_ALERT)){
                    exitProcess(-1)
                } else if(istenilenAlert.equals(AlertDialogEnum.CIKIS_YAP_ALERT)){

                }
            }
        }.create().show()

    }
}