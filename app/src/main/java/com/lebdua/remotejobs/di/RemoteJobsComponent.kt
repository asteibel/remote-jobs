package com.lebdua.remotejobs.di

import android.content.Context
import com.lebdua.remotejobs.feat.jobs.JobListingActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RemoteJobsModule::class,
        NetworkingModule::class
    ]
)
@Singleton
interface RemoteJobsComponent {
    @Component.Builder
    interface Builder {
        fun build(): RemoteJobsComponent

        @BindsInstance
        fun context(context: Context): Builder
    }

    fun inject(activity: JobListingActivity)
}
