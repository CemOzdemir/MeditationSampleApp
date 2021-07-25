package com.e.meditationsampleapp.features.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.e.meditationsampleapp.component.tile.TileData
import com.e.meditationsampleapp.component.tile.setData
import com.e.meditationsampleapp.databinding.ItemMeditationBinding
import com.e.meditationsampleapp.model.MeditationModel

class MeditationListAdapter(
    private var meditationList: ArrayList<MeditationModel>,
    private var itemSelectedListener: ItemSelectListener
) :
    RecyclerView.Adapter<MeditationListAdapter.MeditationViewHolder>() {

    class MeditationViewHolder(
        private val binding: ItemMeditationBinding,
        private val itemSelectedListener: ItemSelectListener
    ) : RecyclerView.ViewHolder(binding.root) {
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
                    itemSelectedListener.onMeditationSelected(meditationModel)
                    val action = DashboardFragmentDirections.actionDashboardFragmentToMediaDetailFragment()
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder =
        MeditationViewHolder(
            ItemMeditationBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemSelectedListener)

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) = holder.bind(meditationList[position])

    fun submitList(list: ArrayList<MeditationModel>) {
        this.meditationList = list
        notifyDataSetChanged()
    }

    fun setItemSelectedListener(itemSelectedListener: ItemSelectListener) {
        this.itemSelectedListener = itemSelectedListener
    }

    override fun getItemCount() = meditationList.size
}