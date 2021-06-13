package com.yigitcanalasoy.spotify.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.yigitcanalasoy.spotify.R
import com.yigitcanalasoy.spotify.databinding.ActivitySplashBinding
import com.yigitcanalasoy.spotify.ui.login.LoginActivity
import com.yigitcanalasoy.spotify.utils.AlertDialogEnum
import com.yigitcanalasoy.spotify.utils.AlertDialogUtil
import com.yigitcanalasoy.spotify.utils.Constants
import com.yigitcanalasoy.spotify.utils.NetworkUtil as NetworkUtil


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            imageViewLogo.setBackgroundResource(R.drawable.spotify_logo)
        }

        timerBaslat()

    }

    private fun timerBaslat() {
        val timer = object: CountDownTimer(Constants.SPLASH_BEKLEME_SURESI.toLong(), Constants.SPLASH_BEKLEME_SURESI_TICK.toLong()){
            override fun onTick(millisUntilFinished: Long) {  }

            override fun onFinish() {
                internetKontrolEt()

            }

        }
        timer.start()
    }

    private fun loginActivityYonlendir() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }

    fun internetKontrolEt(){
        var internetVarmi : Boolean
        var networkUtil = NetworkUtil()
        var alertDialogUtil = AlertDialogUtil()

        internetVarmi = networkUtil.networkControl(this)

        if(internetVarmi)
            loginActivityYonlendir()
        else {
            alertDialogUtil.ortakAlertDialog(
                    AlertDialogEnum.INTERNET_KONTROL_ALERT,
                    this,
                    getString(R.string.internetKontrolAlertDialogTitle),
                    getString(R.string.internetKontrolAlertDialogMessage),
                    getString(R.string.internetKontrolAlertDialogPositiveButtonText),
                    getString(R.string.internetKontrolAlertDialogNegativeButtonText))
        }

    }

}