package com.yigitcanalasoy.spotify.data.datasource.remote

import com.yigitcanalasoy.spotify.data.datasource.KategoriDataSource
import com.yigitcanalasoy.spotify.data.model.KategoriResponseItem
import com.yigitcanalasoy.spotify.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RemoteKategoriDataSource : KategoriDataSource{

    override fun getAllKategori(): Flow<Resource<List<KategoriResponseItem>>> = flow {
        try {

            emit(Resource.Loading())

            val response = RetrofitService.build().getAllKategori()

            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }
        }catch (e:Exception){
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}