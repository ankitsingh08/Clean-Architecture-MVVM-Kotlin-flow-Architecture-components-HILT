package ankit.com.starwarssample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ankit.com.domain.model.CharacterDomainModel
import ankit.com.starwarssample.databinding.CharactersListItemBinding

/**
 * Created by AnkitSingh on 12/12/20.
 */
class StarWarCharactersAdapter (private val listener: OnClickHandler):
        ListAdapter<CharacterDomainModel, RecyclerView.ViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharactersViewHolder(
                CharactersListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)
        (holder as CharactersViewHolder).bind(listener, character)
        holder.itemView.setOnClickListener {
            listener.onItemClick(character)
        }
    }

    class CharactersViewHolder(private val binding: CharactersListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: OnClickHandler, item: CharacterDomainModel) {
            binding.apply {
                character = item
                executePendingBindings()
            }
        }
    }

    interface OnClickHandler {
        fun onItemClick(character: CharacterDomainModel)
    }
}