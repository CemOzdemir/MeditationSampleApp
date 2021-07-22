package com.e.meditationsampleapp.base.repository

import com.e.meditationsampleapp.model.DashboardResponse
import io.reactivex.Single
import retrofit2.http.GET

private const val URL = "api/jsonBlob/a07152f5-775c-11eb-a533-c90b9d55001f"

interface MeditationSampleApi {

    @GET(URL)
    fun getDashboardResponse(): Single<DashboardResponse>
}