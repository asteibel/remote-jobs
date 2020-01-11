package com.lebdua.remotejobs.networking.repositories

import com.lebdua.remotejobs.model.Job
import io.reactivex.disposables.Disposable

interface ApiRepository {

    fun getJobs(
        success: (List<Job>) -> Unit,
        failure: (Throwable) -> Unit
    ): Disposable
}
