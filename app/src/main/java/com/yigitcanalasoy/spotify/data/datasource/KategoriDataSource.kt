package com.yigitcanalasoy.spotify.data.datasource

import com.yigitcanalasoy.spotify.data.model.KategoriResponseItem
import com.yigitcanalasoy.spotify.utils.Resource
import kotlinx.coroutines.flow.Flow

interface KategoriDataSource {
    fun getAllKategori(): Flow<Resource<List<KategoriResponseItem>>>
}