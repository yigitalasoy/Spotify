package com.yigitcanalasoy.spotify.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import com.yigitcanalasoy.spotify.R

class ProgressDialogUtil(val activity: Activity) {
    lateinit var progressDialog:AlertDialog
    fun progressDialogBasla(){
        progressDialog = ProgressDialog(activity,R.style.CustomProgressDialog)
        progressDialog.setMessage(activity.applicationContext.getString(R.string.progressDialogText))
        progressDialog.show()


    }
    fun progressDialogBitir() {
        progressDialog.dismiss()
    }

}