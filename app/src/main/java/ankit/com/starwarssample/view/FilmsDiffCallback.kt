package ankit.com.starwarssample.view

import androidx.recyclerview.widget.DiffUtil
import ankit.com.starwarssample.model.CharacterPresentationModel
import ankit.com.starwarssample.model.FilmsPresentationModel
import ankit.com.starwarssample.model.SpeciesPresentationModel

/**
 * Created by AnkitSingh on 12/12/20.
 */
class FilmsDiffCallback : DiffUtil.ItemCallback<FilmsPresentationModel>() {
    override fun areItemsTheSame(oldItem: FilmsPresentationModel, newItem: FilmsPresentationModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: FilmsPresentationModel, newItem: FilmsPresentationModel): Boolean {
        return oldItem == newItem
    }

}