package com.e.meditationsampleapp.features.dashboard

import android.R.attr.*
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.e.meditationsampleapp.R
import com.e.meditationsampleapp.base.CommonItemSpaceDecoration
import com.e.meditationsampleapp.base.EMPTY
import com.e.meditationsampleapp.base.px
import com.e.meditationsampleapp.component.banner.BannerData
import com.e.meditationsampleapp.component.banner.setData
import com.e.meditationsampleapp.databinding.DashboardFragmentBinding
import com.e.meditationsampleapp.model.MeditationModel
import com.e.meditationsampleapp.model.StoryModel
import java.util.*
import kotlin.collections.ArrayList

private const val GRID_ITEM_HORIZONTAL_SPACE_DP = 12
private const val GRID_ITEM_VERTICAL_SPACE_DP = 24

private const val DATE_PATTERN = "MM/dd/yyyy, EEE"

class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by activityViewModels()
    private lateinit var binding: DashboardFragmentBinding

    private val meditationSelectedListener = object: ItemSelectListener {
        override fun onMeditationSelected(selectedMeditation: MeditationModel) {
            viewModel.run {
                mediaTitleObservable.set(selectedMeditation.title)
                mediaContentObservable.set(selectedMeditation.content)
                mediaDateObservable.set(getFormattedDate(selectedMeditation.releaseDate))
            }
        }
    }

    private val storySelectedListener = object: ItemSelectListener {
        override fun onStorySelected(selectedStory: StoryModel) {
            viewModel.run {
                mediaTitleObservable.set(selectedStory.name)
                mediaContentObservable.set(selectedStory.text)
                mediaDateObservable.set(selectedStory.date)
            }
        }
    }
    private val meditationListAdapter = MeditationListAdapter(arrayListOf(), meditationSelectedListener)
    private val storiesListAdapter = StoryListAdapter(arrayListOf(), storySelectedListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewProperties()
        observeViewModel()

        viewModel.getDashboardData()
    }

    private fun setViewProperties() {
        binding.run {
            viewModel = this@DashboardFragment.viewModel
            meditationList.adapter = meditationListAdapter
            storyList.adapter = storiesListAdapter
            storyList.addItemDecoration(
                CommonItemSpaceDecoration(
                    GRID_ITEM_HORIZONTAL_SPACE_DP.px,
                    GRID_ITEM_VERTICAL_SPACE_DP.px
                )
            )
            banner.setData(BannerData(getString(R.string.banner_description)))
        }
    }

    private fun observeViewModel() {
        viewModel.run {
            submitListToMeditationsAdapterLiveData.observe(viewLifecycleOwner, { list ->
                list?.let { meditationListAdapter.submitList(ArrayList(list)) }
            })
            submitListToStoriesAdapterLiveData.observe(viewLifecycleOwner, { list ->
                list?.let { storiesListAdapter.submitList(ArrayList(list)) }
            })
        }
    }

    private fun getFormattedDate(date: String?) = if (date?.isNotBlank() == true) {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = date.toLong() * 1000L
        DateFormat.format(DATE_PATTERN, calendar).toString()
    } else {
        EMPTY
    }
}