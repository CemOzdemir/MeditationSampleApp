package com.e.meditationsampleapp.component.tile

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class TileViewModel : ViewModel(){

    val titleObservable = ObservableField<String>()
    val subtitleObservable = ObservableField<String>()
    val imageUrlObservable = ObservableField<String>()

    fun handleTileData(tileData: TileData?) {
        tileData?.run {
            titleObservable.set(title)
            subtitleObservable.set(subtitle)
            imageUrlObservable.set(imageUrl)
        }
    }
}