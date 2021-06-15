package com.yigitcanalasoy.spotify.ui.muzikdetay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.yigitcanalasoy.spotify.R
import com.yigitcanalasoy.spotify.data.model.MuzikDetay
import com.yigitcanalasoy.spotify.data.model.Muzikler
import com.yigitcanalasoy.spotify.databinding.ActivityMuzikBinding
import com.yigitcanalasoy.spotify.databinding.ActivityMuzikDetayBinding
import com.yigitcanalasoy.spotify.utils.Constants
import com.yigitcanalasoy.spotify.utils.GlideUtil
import com.yigitcanalasoy.spotify.utils.ObjectUtil
import okhttp3.internal.wait
import java.util.*

class MuzikDetayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMuzikDetayBinding
    var tasinanDegerString : String? = null
    var tasinanMuzik : Muzikler? = null
    var objectUtil = ObjectUtil()
    var isfavori : Boolean = false
    var isPlay : Boolean = true
    var muzikSuresi : Int = 0
    var muzikAnlikDakika : Int = 0
    var muzikAnlikSaniye : Int = 0
    var muzikDakika : Int = 0
    var muzikSaniye : Int = 0
    var muzikAnlikSure : Int = 0
    var glideUtil = GlideUtil()
    lateinit var timer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMuzikDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {


            imageViewFavori.setOnClickListener(View.OnClickListener {
                if(isfavori){
                    imageViewFavori.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
                    isfavori=false
                }
                else{
                    imageViewFavori.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_24))
                    isfavori=true
                }
            })

            imageViewPlayPause.setOnClickListener(View.OnClickListener {
                if(isPlay){
                    imageViewPlayPause.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_play_circle_filled_24))
                    isPlay=false
                    sureyiDurdur()
                }
                else{
                    imageViewPlayPause.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_pause_circle_filled_24))
                    isPlay=true
                    sureyiBaslat()
                }
            })

            seekBarMuzikSure.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                               fromUser: Boolean) {

                    muzikAnlikDakika = progress / Constants.MUZIK_DETAY_SAAT_DAKIKA;
                    muzikAnlikSaniye = progress - (muzikAnlikDakika * Constants.MUZIK_DETAY_SAAT_DAKIKA);

                    muzikAnlikSure = progress

                    binding.textViewAnlikSureDakika.text = muzikAnlikDakika.toString()
                    binding.textViewAnlikSureSaniye.text = muzikAnlikSaniye.toString()

                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {  }

                override fun onStopTrackingTouch(seekBar: SeekBar) {  }
            })

            imageViewBackPress.setOnClickListener {
                onBackPressed()
            }
        }

        init()
    }

    fun init(){
        gelenMuzigiAl()
        sureyiTanimla()
        sureyiBaslat()
    }
    fun gelenMuzigiAl(){
        tasinanDegerString = intent.getStringExtra(Constants.TIKLANAN_MUZIK)
        tasinanMuzik = objectUtil.jsonStringToMuzik(tasinanDegerString)
        gelenMuzigiListele()
    }
    fun gelenMuzigiListele(){
        binding.apply {
            textViewMuzikAdi.text=tasinanMuzik?.muzikAdi
            textViewDinlenmeSayisi.text=tasinanMuzik?.muzikDetay?.dinlenmeSayisi+Constants.MUZIK_DETAY_DINLENME_TEXTBOX
            textViewCikisYili.text=tasinanMuzik?.muzikDetay?.cikisYili
            textViewSarkici.text=tasinanMuzik?.sarkici
            glideUtil.UrldekiResmiAl(tasinanMuzik?.resim.toString(),applicationContext,imageViewMuzik)

            muzikDakika = Integer.parseInt(tasinanMuzik?.muzikDetay?.süresi?.split(":")?.get(Constants.MUZIK_DETAY_TEXTBOX_DAKIKA_ID))
            muzikSaniye = Integer.parseInt(tasinanMuzik?.muzikDetay?.süresi?.split(":")?.get(Constants.MUZIK_DETAY_TEXTBOX_SANIYE_ID))

            muzikSuresi = muzikDakika * Constants.MUZIK_DETAY_SAAT_DAKIKA
            muzikSuresi += muzikSaniye

            seekBarMuzikSure.max = muzikSuresi

            textViewMaxSureDakika.text = muzikDakika.toString()
            textViewMaxSureSaniye.text = muzikSaniye.toString()

        }
    }

    fun sureyiTanimla(){
        timer = object: CountDownTimer(muzikSuresi.toLong()*Constants.MUZIK_DETAY_BEKLEME_SURESI, Constants.MUZIK_DETAY_BEKLEME_SURESI){
            override fun onTick(millisUntilFinished: Long) {

                if(muzikAnlikSure < muzikSuresi){
                    muzikAnlikSure++
                    binding.textViewAnlikSureDakika.text = (muzikAnlikSure / Constants.MUZIK_DETAY_SAAT_DAKIKA).toString()
                    binding.textViewAnlikSureSaniye.text = (muzikAnlikSure - (Integer.parseInt(binding.textViewAnlikSureDakika.text.toString()) * Constants.MUZIK_DETAY_SAAT_DAKIKA)).toString()
                    binding.seekBarMuzikSure.progress = binding.seekBarMuzikSure.progress + Constants.MUZIK_DETAY_SEEKBAR_ILERLEME

                } else {
                    timer.onFinish()
                }


            }

            override fun onFinish() {

            }

        }
    }

    fun sureyiBaslat(){
        timer.start()
    }

    fun sureyiDurdur(){
        timer.cancel()
    }

}