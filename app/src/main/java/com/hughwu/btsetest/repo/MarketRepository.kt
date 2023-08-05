package com.hughwu.btsetest.repo

import com.hughwu.btsetest.api.RetrofitAPI
import com.hughwu.btsetest.model.MarketResponse
import com.hughwu.btsetest.util.Resource
import javax.inject.Inject

class MarketRepository @Inject constructor(
    private val retrofitApi: RetrofitAPI
) :MarketRepositoryInterface {
    override suspend fun getMarketList(): Resource<MarketResponse> {
        return try{
            val response = retrofitApi.getMarket()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        }catch (e: Exception){
            Resource.error("No data!", null)
        }
    }
}