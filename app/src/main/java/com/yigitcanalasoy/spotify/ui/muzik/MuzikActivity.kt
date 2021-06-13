package com.yigitcanalasoy.spotify.ui.muzik

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yigitcanalasoy.spotify.data.model.Muzikler
import com.yigitcanalasoy.spotify.databinding.ActivityMuzikBinding
import com.yigitcanalasoy.spotify.ui.muzikdetay.MuzikDetayActivity
import com.yigitcanalasoy.spotify.utils.*

class MuzikActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMuzikBinding

    var tasinanDegerString : String? = null
    var tasinanMuzikList : List<Muzikler>? = null
    var objectUtil = ObjectUtil();
    var progressDialog = ProgressDialogUtil(this@MuzikActivity)
    private lateinit var muzikAdapter : MuzikAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMuzikBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.apply {
            progressDialog.progressDialogBasla()

            switchListelemeTuru.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    recyclerViewMuzikler.layoutManager = StaggeredGridLayoutManager(Constants.RECYCLERVIEWMUZIKLERSPANCOUNT, StaggeredGridLayoutManager.VERTICAL)
                } else {
                    recyclerViewMuzikler.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                }
            }

            spinnerSiralamaTuru.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    muzikleriSirala(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {  }

            }

            tiklananKategoriMuzikleriAl()
            initRecyclerView()


        }
    }

    private fun muzikleriSirala(position: Int) {
        if (position.equals(SiralamaTuruEnum.A_DAN_Z_YE_SIRALA.gelenDeger))
            tasinanMuzikList = tasinanMuzikList?.sortedBy { it.muzikAdi }
        else if (position.equals(SiralamaTuruEnum.Z_DEN_A_YA_SIRALA.gelenDeger))
            tasinanMuzikList = tasinanMuzikList?.sortedByDescending { it.muzikAdi }

        muzikAdapter.setData(tasinanMuzikList)
        muzikAdapter.notifyDataSetChanged()
    }

    fun tiklananKategoriMuzikleriAl() {
        tasinanDegerString = intent.getStringExtra(Constants.TIKLANAN_KATEGORI_MUZIKLERI)
        tasinanMuzikList = objectUtil.jsonStringToMuzikArray(tasinanDegerString)
    }


    fun initRecyclerView(){
        binding.apply {
            muzikAdapter = MuzikAdapter(tasinanMuzikList, object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    var muzikDetayActivityIntent = Intent(this@MuzikActivity, MuzikDetayActivity::class.java)
                    var objectUtil = ObjectUtil()
                    muzikDetayActivityIntent.putExtra(Constants.TIKLANAN_MUZIK, objectUtil.muzikToJsonString(tasinanMuzikList?.get(position)))
                    startActivity(muzikDetayActivityIntent)
                }
            },applicationContext)
            recyclerViewMuzikler.adapter = muzikAdapter
            recyclerViewMuzikler.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        }
        progressDialog.progressDialogBitir()
    }


}