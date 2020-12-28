package ankit.com.starwarssample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ankit.com.starwarssample.databinding.SpeciesItemBinding
import ankit.com.starwarssample.model.SpeciesPresentationModel

/**
 * Created by AnkitSingh on 12/12/20.
 */
class SpeciesAdapter : ListAdapter<SpeciesPresentationModel, RecyclerView.ViewHolder>(SpeciesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SpeciesViewHolder(
            SpeciesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val species = getItem(position)
        (holder as SpeciesViewHolder).bind(species)
    }

    class SpeciesViewHolder(private val binding: SpeciesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SpeciesPresentationModel) {
            binding.apply {
                species = item
                executePendingBindings()
            }
        }
    }
}