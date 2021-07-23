package com.e.meditationsampleapp.features.dashboard

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.meditationsampleapp.base.repository.MeditationSampleApiService
import com.e.meditationsampleapp.component.banner.BannerData
import com.e.meditationsampleapp.component.tile.TileData
import com.e.meditationsampleapp.model.DashboardResponse
import com.e.meditationsampleapp.model.MeditationModel
import com.e.meditationsampleapp.model.StoryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DashboardViewModel : ViewModel() {
    private var disposable = CompositeDisposable()
    private var apiService = MeditationSampleApiService()
    var dashboardData: DashboardResponse? = null

    private val submitListToMeditationsAdapter = MutableLiveData<List<MeditationModel>>()
    val submitListToMeditationsAdapterLiveData: LiveData<List<MeditationModel>> get() = submitListToMeditationsAdapter

    private val submitListToStoriesAdapter = MutableLiveData<List<StoryModel>>()
    val submitListToStoriesAdapterLiveData: LiveData<List<StoryModel>> get() = submitListToStoriesAdapter

    val progressBarVisibilityObservable = ObservableBoolean()
    val bannerVisibilityObservable = ObservableBoolean()
    val storiesVisibilityObservable = ObservableBoolean()
    val meditationsVisibilityObservable = ObservableBoolean()

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
        dashboardData = dashboardResponse
        dashboardResponse.run {
            isBannerEnabled?.let { bannerVisibilityObservable.set(it) }
            meditations?.let {submitListToMeditationsAdapter.value = it }
            meditationsVisibilityObservable.set(meditations?.isNotEmpty() == true)
            stories?.let { submitListToStoriesAdapter.value = it }
            storiesVisibilityObservable.set(stories?.isNotEmpty() == true)
        }
    }
}