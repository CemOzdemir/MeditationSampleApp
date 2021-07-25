package com.e.meditationsampleapp.features.mediadetail

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.e.meditationsampleapp.R
import com.e.meditationsampleapp.databinding.MediaDetailFragmentBinding
import com.e.meditationsampleapp.features.dashboard.DashboardViewModel
import java.io.IOException


private const val MEDIA_URL = "https://d2r0ihkco3hemf.cloudfront.net/bgxupraW2spUpr_y2.mp3"

class MediaDetailFragment : Fragment() {

    private lateinit var binding: MediaDetailFragmentBinding

    private val viewModel: DashboardViewModel by activityViewModels()
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.media_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDashboardData()
        setViewProperties()
        setMediaPlayer()
    }

    private fun setViewProperties() {
        binding.viewModel = viewModel
        binding.playButton.setOnClickListener {
            playPauseMedia()
        }
    }

    private fun setMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setDataSource(MEDIA_URL)
        }
    }

    private fun playPauseMedia() {
        mediaPlayer?.run {
            try {
                if (mediaPlayer?.isPlaying == true) {
                    stop()
                } else {
                    prepare()
                    start()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
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