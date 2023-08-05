package com.hughwu.btsetest.repo

import com.hughwu.btsetest.model.MarketResponse
import com.hughwu.btsetest.util.Resource

interface MarketRepositoryInterface {
    suspend fun getMarketList():Resource<MarketResponse>
}