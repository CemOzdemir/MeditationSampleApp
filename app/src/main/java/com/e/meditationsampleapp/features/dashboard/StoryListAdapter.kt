package com.e.meditationsampleapp.features.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.meditationsampleapp.component.tile.TileData
import com.e.meditationsampleapp.component.tile.setData
import com.e.meditationsampleapp.databinding.ItemStoryBinding
import com.e.meditationsampleapp.model.StoryModel

class StoryListAdapter(private var storyList: ArrayList<StoryModel>):
    RecyclerView.Adapter<StoryListAdapter.StoryViewHolder>() {

    class StoryViewHolder(private val binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(storyModel: StoryModel) {
            binding.run {
                tileView.setData(
                    TileData(
                        title = storyModel.name,
                        subtitle = storyModel.category,
                        imageUrl = storyModel.image?.small
                    )
                )
                tileView.setOnClickListener {
//                    val action = MovieListFragmentDirections.actionToMovieDetailFragment(movieItem)
//                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder =
        StoryViewHolder(ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) = holder.bind(storyList[position])

    fun submitList(list: ArrayList<StoryModel>) {
        this.storyList = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = storyList.size
}