package ankit.com.starwarssample.view.charactersearch

import androidx.recyclerview.widget.DiffUtil
import ankit.com.starwarssample.model.CharacterPresentationModel

/**
 * Created by AnkitSingh on 12/12/20.
 */
class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterPresentationModel>() {
    override fun areItemsTheSame(oldItem: CharacterPresentationModel, newItem: CharacterPresentationModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterPresentationModel, newItem: CharacterPresentationModel): Boolean {
        return oldItem == newItem
    }

}