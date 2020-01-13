package com.lebdua.remotejobs.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.lebdua.remotejobs.custom_tabs.CustomTabActivityHelper
import com.lebdua.remotejobs.networking.repositories.ApiRepository
import com.lebdua.remotejobs.utils.Toaster
import com.lebdua.remotejobs.utils.ToasterImpl
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

    @Provides
    fun provideCustomTabActivityHelper(): CustomTabActivityHelper {
        return CustomTabActivityHelper()
    }

    @Provides
    fun provideToaster(context: Context): Toaster {
        return ToasterImpl(context)
    }
}