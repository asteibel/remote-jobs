package com.lebdua.remotejobs.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.lebdua.remotejobs.networking.repositories.ApiRepository
import com.lebdua.remotejobs.vm.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteJobsModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(
        context: Context,
        apiRepository: ApiRepository
    ): ViewModelProvider.Factory {
        return ViewModelFactory(
            context,
            apiRepository
        )
    }
}