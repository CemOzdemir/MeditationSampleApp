package com.e.meditationsampleapp.features.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.e.meditationsampleapp.model.DashboardResponse
import com.e.meditationsampleapp.model.MeditationModel
import com.e.meditationsampleapp.model.StoryModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy

class DashboardViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var viewModel = DashboardViewModel()
    private var spyViewModel = spy(viewModel)

    private val mockMeditation = mock<MeditationModel>()
    private val mockStory = mock<StoryModel>()
    private val dashboardResponse = DashboardResponse(
        isBannerEnabled = true,
        meditations = listOf(mockMeditation),
        stories = listOf(mockStory)
    )
    private val dashboardResponseCase2 = DashboardResponse(
        isBannerEnabled = false,
        meditations = null,
        stories = null
    )

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker() =  ExecutorScheduler.ExecutorWorker({ it.run() }, true)
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @Test
    fun testGetDashboardData() {
        // When
        spyViewModel.getDashboardData()

        // Then
        assertFalse(viewModel.progressBarVisibilityObservable.get())
        verify(spyViewModel).handleDashboardData(any())
    }

    @Test
    fun testHandleDashboardData() {
        // When
        viewModel.handleDashboardData(dashboardResponse)

        // Then
        assertTrue(viewModel.bannerVisibilityObservable.get())
        assertTrue(viewModel.meditationsVisibilityObservable.get())
        assertTrue(viewModel.storiesVisibilityObservable.get())

        // When
        viewModel.handleDashboardData(dashboardResponseCase2)

        // Then
        assertFalse(viewModel.bannerVisibilityObservable.get())
        assertFalse(viewModel.meditationsVisibilityObservable.get())
        assertFalse(viewModel.storiesVisibilityObservable.get())
    }
}