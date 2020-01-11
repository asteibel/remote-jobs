package com.lebdua.remotejobs.networking.repositories

import com.lebdua.remotejobs.model.Job
import com.lebdua.remotejobs.networking.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ApiRepositoryImpl(
    private val apiService: ApiService
) : ApiRepository {

    override fun getJobs(
        success: (List<Job>) -> Unit,
        failure: (Throwable) -> Unit
    ): Disposable {
        return apiService.getJobList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    success.invoke(emptyList())
                },
                {
                    failure.invoke(it)
                }
            )
    }
}
