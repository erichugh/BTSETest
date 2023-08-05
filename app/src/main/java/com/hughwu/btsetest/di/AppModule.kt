package com.hughwu.btsetest.di

import com.hughwu.btsetest.api.RetrofitAPI
import com.hughwu.btsetest.repo.MarketRepository
import com.hughwu.btsetest.repo.MarketRepositoryInterface
import com.hughwu.btsetest.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun injectRetrofitAPI(): RetrofitAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Util.BASE_URL).build().create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectRepo(api: RetrofitAPI) = MarketRepository(api) as MarketRepositoryInterface
}