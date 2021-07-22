package com.e.meditationsampleapp.model

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("small") val small: String?,
    @SerializedName("large") val large: String?
)
