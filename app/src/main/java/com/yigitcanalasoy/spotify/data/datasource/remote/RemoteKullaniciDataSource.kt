package com.yigitcanalasoy.spotify.data.datasource.remote

import com.yigitcanalasoy.spotify.data.datasource.KullaniciDataSource
import com.yigitcanalasoy.spotify.data.model.KullaniciResponseItem
import com.yigitcanalasoy.spotify.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RemoteKullaniciDataSource : KullaniciDataSource {

    override fun getAllKullanici(): Flow<Resource<List<KullaniciResponseItem>>> = flow {
        try {

            emit(Resource.Loading())

            val response = RetrofitService.build().getAllKullanici()

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