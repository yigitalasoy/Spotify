package com.yigitcanalasoy.spotify.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yigitcanalasoy.spotify.data.model.KullaniciResponseItem
import com.yigitcanalasoy.spotify.data.repository.KullaniciRepository
import com.yigitcanalasoy.spotify.utils.ResourceStatus
import kotlinx.coroutines.launch

class KullaniciViewModel : ViewModel() {

    private  val kullaniciRepository: KullaniciRepository = KullaniciRepository()

    init {
        getAllKullanici()
    }


    var allKullaniciLiveData = MutableLiveData<List<KullaniciResponseItem>>()
    var error =    MutableLiveData<Throwable>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()

    fun getAllKullanici()  = viewModelScope.launch {

        kullaniciRepository.getAllKullanici()

                .asLiveData(viewModelScope.coroutineContext).observeForever {

                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            loading?.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {
                            allKullaniciLiveData?.postValue(it.data!!)
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