package com.hughwu.btsetest.api

import com.hughwu.btsetest.model.MarketResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("/futures/api/inquire/initial/market")
    suspend fun getMarket():Response<MarketResponse>
}