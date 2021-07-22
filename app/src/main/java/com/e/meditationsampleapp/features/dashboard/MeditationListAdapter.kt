package com.e.meditationsampleapp.features.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.meditationsampleapp.component.tile.TileData
import com.e.meditationsampleapp.component.tile.setData
import com.e.meditationsampleapp.databinding.ItemMeditationBinding
import com.e.meditationsampleapp.model.MeditationModel

class MeditationListAdapter(private var meditationList: ArrayList<MeditationModel>):
    RecyclerView.Adapter<MeditationListAdapter.MeditationViewHolder>() {

    class MeditationViewHolder(private val binding: ItemMeditationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meditationModel: MeditationModel) {
            binding.run {
                tileView.setData(
                    TileData(
                        title = meditationModel.title,
                        subtitle = meditationModel.subtitle,
                        imageUrl = meditationModel.image?.small
                    )
                )
                tileView.setOnClickListener {
//                    val action = MovieListFragmentDirections.actionToMovieDetailFragment(movieItem)
//                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder =
        MeditationViewHolder(ItemMeditationBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) = holder.bind(meditationList[position])

    fun submitList(list: ArrayList<MeditationModel>) {
        this.meditationList = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = meditationList.size
}