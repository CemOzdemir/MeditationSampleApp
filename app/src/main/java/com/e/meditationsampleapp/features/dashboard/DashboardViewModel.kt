package com.e.meditationsampleapp.features.dashboard

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.meditationsampleapp.base.repository.MeditationSampleApiService
import com.e.meditationsampleapp.component.tile.TileData
import com.e.meditationsampleapp.model.DashboardResponse
import com.e.meditationsampleapp.model.MeditationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DashboardViewModel : ViewModel() {
    private var disposable = CompositeDisposable()
    private var apiService = MeditationSampleApiService()

    private val submitListToAdapter = MutableLiveData<List<MeditationModel>>()
    val submitListToAdapterLiveData: LiveData<List<MeditationModel>> get() = submitListToAdapter

    val progressBarVisibilityObservable = ObservableBoolean(false)
    val tileDataObservable = ObservableField<TileData>()

    fun getDashboardData() {
        progressBarVisibilityObservable.set(true)
        disposable.add(
            apiService.getDashboardResponse()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DashboardResponse>() {
                    override fun onSuccess(dashboardResponse: DashboardResponse) {
                        progressBarVisibilityObservable.set(false)
                        handleDashboardData(dashboardResponse)
                    }

                    override fun onError(e: Throwable) {
                        progressBarVisibilityObservable.set(false)
                    }
                })
        )
    }

    fun handleDashboardData(dashboardResponse: DashboardResponse) {
        dashboardResponse.meditations?.run {
            submitListToAdapter.value = this
        }
    }
}