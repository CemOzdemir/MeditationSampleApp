package com.e.meditationsampleapp.model

import com.google.gson.annotations.SerializedName

data class DashboardResponse(
    @SerializedName("isBannerEnabled") val isBannerEnabled: Boolean?,
    @SerializedName("meditations") val meditations: List<MeditationModel>?,
    @SerializedName("stories") val stories: List<StoryModel>?
)
