package com.yigitcanalasoy.spotify.ui.kategori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yigitcanalasoy.spotify.R
import com.yigitcanalasoy.spotify.data.model.KategoriResponseItem
import com.yigitcanalasoy.spotify.databinding.ActivityKategoriBinding
import com.yigitcanalasoy.spotify.ui.muzik.MuzikActivity
import com.yigitcanalasoy.spotify.utils.*

class KategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKategoriBinding
    var kategoriViewModel: KategoriViewModel?=null
    private lateinit var kategoriAdapter : KategoriAdapter
    var kategoriList:List<KategoriResponseItem> = emptyList()
    var filtrelenmisKategoriListe: List<KategoriResponseItem> = emptyList()
    var progressDialogUtil = ProgressDialogUtil(this@KategoriActivity)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.apply {
            searchViewKategoriAra.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean
                {
                    kategoriFiltrele(newText)
                    return false
                }
            })

        }

    }

    fun init(){
        progressDialogUtil.progressDialogBasla()
        initViewModel()
    }


    fun initRecyclerView(kategoriList : List<KategoriResponseItem>) {
        binding.apply {
            kategoriAdapter = KategoriAdapter(kategoriList, object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    if(filtrelenmisKategoriListe.isNotEmpty()){
                        muzikActivityAc(filtrelenmisKategoriListe, position)
                    } else {
                        muzikActivityAc(kategoriList, position)
                    }
                    searchViewKategoriAra.isFocusable = false
                    searchViewKategoriAra.isSelected = false
                }
            },applicationContext)
            recyclerViewKategoriler.adapter = kategoriAdapter
            recyclerViewKategoriler.layoutManager = StaggeredGridLayoutManager(Constants.RECYCLERVIEWKATEGORILERSPANCOUNT, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun muzikActivityAc(kategoriList: List<KategoriResponseItem>?, position: Int) {
        var muzikActivityIntent = Intent(this@KategoriActivity, MuzikActivity::class.java)
        var objectUtil = ObjectUtil()
        muzikActivityIntent.putExtra(Constants.TIKLANAN_KATEGORI_MUZIKLERI, objectUtil.muzikArrayToJsonString(kategoriList?.get(position)?.muzikler))
        startActivity(muzikActivityIntent)
    }

    fun initViewModel()
    {
        kategoriViewModel= KategoriViewModel()
        kategoriViewModel?.apply {
            allKategoriLiveData?.observe(this@KategoriActivity, Observer {
                it.run {
                    kategoriList=it
                    initRecyclerView(it)
                    progressDialogUtil.progressDialogBitir()
                }
            })
            error?.observe(this@KategoriActivity, Observer {
                it.run {
                }
            })
            loading?.observe(this@KategoriActivity, Observer {
            })
        }
    }

    fun kategoriFiltrele(istenilenKategoriAdi : String?){
        istenilenKategoriAdi?.let {
            kategoriList?.let {
                if(istenilenKategoriAdi.trim().isEmpty()){
                    filtrelenmisKategoriListe= kategoriList
                    kategoriAdapter.setData(filtrelenmisKategoriListe)
                } else {
                    var filtrelenmisKategoriList: List<KategoriResponseItem> = it.filter { it.kategoriAd!!.toLowerCase().contains(istenilenKategoriAdi.toLowerCase()) }
                    kategoriAdapter.setData(filtrelenmisKategoriList)
                    filtrelenmisKategoriListe=filtrelenmisKategoriList
                }
                kategoriAdapter.notifyDataSetChanged()

            }
        }
    }

    override fun onBackPressed() {
        var alertDialogUtil = AlertDialogUtil()

        alertDialogUtil.ortakAlertDialog(
                AlertDialogEnum.CIKIS_YAP_ALERT,
                this,
                getString(R.string.cikisYapAlertDialogTitle),
                getString(R.string.cikisYapAlertDialogMessage),
                getString(R.string.cikisYapAlertDialogPositiveButtonText),
                getString(R.string.cikisYapAlertDialogNegativeButtonText))
    }


}