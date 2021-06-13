package com.yigitcanalasoy.spotify.data.datasource.remote
import com.yigitcanalasoy.spotify.utils.Constants

import com.yigitcanalasoy.spotify.data.model.KategoriResponseItem
import com.yigitcanalasoy.spotify.data.model.KullaniciResponseItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET(Constants.KATEGORILER_JSON)
    suspend fun getAllKategori(): Response<List<KategoriResponseItem>>

    @GET(Constants.KULLANICILAR_JSON)
    suspend fun getAllKullanici(): Response<List<KullaniciResponseItem>>


    companion object  {

        fun build(): RetrofitService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHtppClient =  OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .client(okHtppClient)
                    .build()

            return retrofit.create(RetrofitService::class.java)
        }
    }
}