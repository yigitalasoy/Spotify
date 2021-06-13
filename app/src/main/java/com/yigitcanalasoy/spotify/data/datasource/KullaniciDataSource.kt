package com.yigitcanalasoy.spotify.data.datasource

import com.yigitcanalasoy.spotify.data.model.KullaniciResponseItem
import com.yigitcanalasoy.spotify.utils.Resource
import kotlinx.coroutines.flow.Flow

interface KullaniciDataSource {
    fun getAllKullanici(): Flow<Resource<List<KullaniciResponseItem>>>
}