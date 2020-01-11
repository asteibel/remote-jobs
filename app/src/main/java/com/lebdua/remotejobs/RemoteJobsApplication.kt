package com.lebdua.remotejobs

import android.app.Application
import com.lebdua.remotejobs.di.DaggerRemoteJobsComponent
import com.lebdua.remotejobs.di.RemoteJobsComponent
import com.lebdua.remotejobs.di.RemoteJobsComponentProvider

class RemoteJobsApplication : Application(),
    RemoteJobsComponentProvider {

    private lateinit var remoteJobsComponent: RemoteJobsComponent

    override fun provideRemoteJobsComponent(): RemoteJobsComponent {
        if (!this::remoteJobsComponent.isInitialized) {
            remoteJobsComponent = DaggerRemoteJobsComponent.builder()
                .context(this)
                .build()
        }
        return remoteJobsComponent
    }
}