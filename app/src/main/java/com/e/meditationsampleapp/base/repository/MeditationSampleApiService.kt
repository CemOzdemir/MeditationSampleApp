package com.e.meditationsampleapp.base.repository

import com.e.meditationsampleapp.model.DashboardResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonblob.com/"

class MeditationSampleApiService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MeditationSampleApi::class.java)

    fun getDashboardResponse(): Single<DashboardResponse> = api.getDashboardResponse()
}