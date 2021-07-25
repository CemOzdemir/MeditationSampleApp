package com.e.meditationsampleapp.features.dashboard

import com.e.meditationsampleapp.model.MeditationModel
import com.e.meditationsampleapp.model.StoryModel

interface ItemSelectListener {
    fun onMeditationSelected(selectedMeditation: MeditationModel) = Unit
    fun onStorySelected(selectedStory: StoryModel) = Unit
}