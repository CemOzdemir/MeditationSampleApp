package com.e.meditationsampleapp.features.dashboard

import android.R.attr.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.e.meditationsampleapp.R
import com.e.meditationsampleapp.base.CommonItemSpaceDecoration
import com.e.meditationsampleapp.base.px
import com.e.meditationsampleapp.component.banner.BannerData
import com.e.meditationsampleapp.component.banner.setData
import com.e.meditationsampleapp.databinding.DashboardFragmentBinding

private const val GRID_ITEM_HORIZONTAL_SPACE_DP = 12
private const val GRID_ITEM_VERTICAL_SPACE_DP = 24

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: DashboardFragmentBinding

    private val meditationListAdapter = MeditationListAdapter(arrayListOf())
    private val storiesListAdapter = StoryListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

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
}