package com.yigitcanalasoy.spotify.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.yigitcanalasoy.spotify.R
import com.yigitcanalasoy.spotify.databinding.ActivityLoginBinding
import com.yigitcanalasoy.spotify.ui.kategori.KategoriActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    var kullaniciViewModel: KullaniciViewModel?=null
    var kullanicilar: HashMap<String,String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            initViewModel()
            imageViewLogo.setBackgroundResource(R.drawable.spotify_logo)
            buttonOturumAc.setOnClickListener {
                if(editTextEPosta.text.trim().isEmpty() || editTextParola.text.trim().isEmpty()){
                    Toast.makeText(this@LoginActivity.applicationContext,getString(R.string.loginActivityBosAlanText),Toast.LENGTH_SHORT).show()
                } else {
                    editTextEPosta.text.toString().trim().let {
                        editTextParola.text.toString().trim().let {
                            loginYap(editTextEPosta.text.toString(), editTextParola.text.toString())
                        }
                    }
                }
            }
        }
    }

    fun loginYap(email :String, parola: String){
        kullanicilar.forEach {
            if (it.key.equals(email) && it.value.equals(parola)){
                Toast.makeText(applicationContext,getString(R.string.loginActivityGirisBasariliText),Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity, KategoriActivity::class.java))
                finish()
                return
            }
        }
        Toast.makeText(this@LoginActivity.applicationContext,getString(R.string.loginActivityGirisBasarisizText),Toast.LENGTH_SHORT).show()

    }

    fun initViewModel()
    {
        kullaniciViewModel= KullaniciViewModel()
        kullaniciViewModel?.apply {
            allKullaniciLiveData?.observe(this@LoginActivity, Observer {
                it.run {
                    it.forEach {
                        kullanicilar.put(it.eMail.toString(),it.parola.toString())
                    }
                }
            })
            error?.observe(this@LoginActivity, Observer {
                it.run {
                }
            })
            loading?.observe(this@LoginActivity, Observer {
            })
        }
    }

}