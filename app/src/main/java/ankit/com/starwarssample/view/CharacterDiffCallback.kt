package ankit.com.starwarssample.view

import androidx.recyclerview.widget.DiffUtil
import ankit.com.domain.model.CharacterDomainModel

/**
 * Created by AnkitSingh on 12/12/20.
 */
class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterDomainModel>() {
    override fun areItemsTheSame(oldItem: CharacterDomainModel, newItem: CharacterDomainModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterDomainModel, newItem: CharacterDomainModel): Boolean {
        return oldItem == newItem
    }

}