package com.e.meditationsampleapp.model

import com.google.gson.annotations.SerializedName

data class StoryModel(
    @SerializedName("name") val name: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("image") val image: ImageModel?
)
