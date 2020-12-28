package ankit.com.starwarssample.view

import androidx.recyclerview.widget.DiffUtil
import ankit.com.starwarssample.model.CharacterPresentationModel
import ankit.com.starwarssample.model.SpeciesPresentationModel

/**
 * Created by AnkitSingh on 12/12/20.
 */
class SpeciesDiffCallback : DiffUtil.ItemCallback<SpeciesPresentationModel>() {
    override fun areItemsTheSame(oldItem: SpeciesPresentationModel, newItem: SpeciesPresentationModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: SpeciesPresentationModel, newItem: SpeciesPresentationModel): Boolean {
        return oldItem == newItem
    }

}