package com.yigitcanalasoy.spotify.data.repository

import com.yigitcanalasoy.spotify.data.datasource.KullaniciDataSource
import com.yigitcanalasoy.spotify.data.datasource.remote.RemoteKullaniciDataSource
import com.yigitcanalasoy.spotify.data.model.KullaniciResponseItem
import com.yigitcanalasoy.spotify.utils.Resource
import kotlinx.coroutines.flow.Flow

class KullaniciRepository {

    private var kullaniciDataSource: KullaniciDataSource?=null

    init {
        kullaniciDataSource= RemoteKullaniciDataSource()
    }

    fun getAllKullanici(): Flow<Resource<List<KullaniciResponseItem>>>
    {
        return kullaniciDataSource!!.getAllKullanici()
    }
}