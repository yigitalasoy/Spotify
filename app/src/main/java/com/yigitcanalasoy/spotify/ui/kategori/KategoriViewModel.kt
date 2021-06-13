package com.yigitcanalasoy.spotify.ui.kategori

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yigitcanalasoy.spotify.data.model.KategoriResponseItem
import com.yigitcanalasoy.spotify.data.repository.KategoriRepository
import com.yigitcanalasoy.spotify.utils.ResourceStatus
import kotlinx.coroutines.launch

class KategoriViewModel : ViewModel() {


    private  val kategoriRepository: KategoriRepository = KategoriRepository()

    init {
        getAllKategori()
    }


    var allKategoriLiveData = MutableLiveData<List<KategoriResponseItem>>()
    var error =    MutableLiveData<Throwable>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()

    fun getAllKategori()  = viewModelScope.launch {

        kategoriRepository.getAllKategori()

                .asLiveData(viewModelScope.coroutineContext).observeForever {

                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            loading?.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {
                            allKategoriLiveData?.postValue(it.data!!)
                            loading?.postValue(false)
                        }

                        ResourceStatus.ERROR -> {
                            Log.e("ERROR", "${it.throwable}")
                            error?.postValue(it.throwable!!)
                            loading?.postValue(false)
                        }
                    }
                }
    }
}