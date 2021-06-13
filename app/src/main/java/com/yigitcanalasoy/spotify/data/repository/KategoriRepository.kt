package com.yigitcanalasoy.spotify.data.repository

import com.yigitcanalasoy.spotify.data.datasource.KategoriDataSource
import com.yigitcanalasoy.spotify.data.datasource.remote.RemoteKategoriDataSource
import com.yigitcanalasoy.spotify.data.model.KategoriResponseItem
import com.yigitcanalasoy.spotify.utils.Resource
import kotlinx.coroutines.flow.Flow

class KategoriRepository {

    private var kategoriDataSource: KategoriDataSource?=null

    init {
        kategoriDataSource= RemoteKategoriDataSource()
    }

    fun getAllKategori(): Flow<Resource<List<KategoriResponseItem>>>
    {
        return kategoriDataSource!!.getAllKategori()
    }

}