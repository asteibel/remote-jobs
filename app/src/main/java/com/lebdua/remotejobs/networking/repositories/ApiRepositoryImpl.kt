package com.lebdua.remotejobs.networking.repositories

import com.google.gson.Gson
import com.lebdua.remotejobs.model.Job
import com.lebdua.remotejobs.networking.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ApiRepositoryImpl(
    private val apiService: ApiService
) : ApiRepository {

    private val gson = Gson()

    override fun getJobs(
        success: (List<Job>) -> Unit,
        failure: (Throwable) -> Unit
    ): Disposable {
        return apiService.getJobList()
            .subscribeOn(Schedulers.io())
            .map {
                it.drop(1)
                    .map { jobAsJson ->
                        gson.fromJson(jobAsJson, Job::class.java)
                    }
                    .filter { job ->
                        job.company.isNotBlank()
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    success.invoke(it)
                },
                {
                    failure.invoke(it)
                }
            )
    }
}
