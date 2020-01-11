package com.lebdua.remotejobs.di

import com.lebdua.remotejobs.networking.Retrofit
import com.lebdua.remotejobs.networking.repositories.ApiRepository
import com.lebdua.remotejobs.networking.repositories.ApiRepositoryImpl
import com.lebdua.remotejobs.networking.services.ApiService
import dagger.Module
import dagger.Provides

@Module
class NetworkingModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.createRetrofitService(ApiService::class.java)
    }

    @Provides
    fun provideApiRepository(apiService: ApiService): ApiRepository {
        return ApiRepositoryImpl(apiService)
    }
}
