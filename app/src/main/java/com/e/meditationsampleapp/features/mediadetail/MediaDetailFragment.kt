package com.e.meditationsampleapp.features.mediadetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.e.meditationsampleapp.R
import com.e.meditationsampleapp.databinding.MediaDetailFragmentBinding
import com.e.meditationsampleapp.features.dashboard.DashboardViewModel

class MediaDetailFragment : Fragment() {

    private lateinit var binding: MediaDetailFragmentBinding
    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.media_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

//        setViewProperties()

        if (viewModel.dashboardData != null) {
            viewModel.getDashboardData()
        }
    }

    override fun onPause() {
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        super.onPause()
    }

    override fun onResume() {
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        super.onResume()
    }
}