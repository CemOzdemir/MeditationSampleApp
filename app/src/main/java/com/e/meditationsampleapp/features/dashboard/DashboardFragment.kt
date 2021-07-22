package com.e.meditationsampleapp.features.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.meditationsampleapp.R
import com.e.meditationsampleapp.databinding.DashboardFragmentBinding

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: DashboardFragmentBinding

    private val meditationListAdapter = MeditationListAdapter(arrayListOf())

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
        binding.viewModel = viewModel

        binding.meditationList.run {
            adapter = meditationListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.run {
            submitListToAdapterLiveData.observe(viewLifecycleOwner, { list ->
                list?.let { meditationListAdapter.submitList(ArrayList(list)) }
            })
        }
    }
}