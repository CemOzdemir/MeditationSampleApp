package com.e.meditationsampleapp.features.dashboard

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.e.meditationsampleapp.base.repository.MeditationSampleApiService
import com.e.meditationsampleapp.model.DashboardResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DashboardViewModel : ViewModel() {
    private var disposable = CompositeDisposable()
    private var apiService = MeditationSampleApiService()

    val progressBarVisibilityObservable = ObservableBoolean(false)

    fun getDashboardData() {
        progressBarVisibilityObservable.set(true)
        disposable.add(
            apiService.getDashboardResponse()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DashboardResponse>() {
                    override fun onSuccess(dashboardResponse: DashboardResponse) {
                        progressBarVisibilityObservable.set(false)
                    }

                    override fun onError(e: Throwable) {
                        progressBarVisibilityObservable.set(false)
                    }
                })
        )
    }
}