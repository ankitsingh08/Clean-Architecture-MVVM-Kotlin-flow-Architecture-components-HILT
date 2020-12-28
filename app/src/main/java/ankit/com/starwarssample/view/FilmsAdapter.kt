package ankit.com.starwarssample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ankit.com.starwarssample.databinding.FilmItemBinding
import ankit.com.starwarssample.databinding.SpeciesItemBinding
import ankit.com.starwarssample.model.FilmsPresentationModel
import ankit.com.starwarssample.model.SpeciesPresentationModel

/**
 * Created by AnkitSingh on 12/28/20.
 */
class FilmsAdapter : ListAdapter<FilmsPresentationModel, RecyclerView.ViewHolder>(FilmsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(
            FilmItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val film = getItem(position)
        (holder as FilmViewHolder).bind(film)
    }

    class FilmViewHolder(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilmsPresentationModel) {
            binding.apply {
                film = item
                executePendingBindings()
            }
        }
    }
}