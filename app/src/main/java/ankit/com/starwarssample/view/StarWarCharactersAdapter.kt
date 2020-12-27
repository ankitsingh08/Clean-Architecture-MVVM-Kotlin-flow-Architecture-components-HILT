package ankit.com.starwarssample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ankit.com.starwarssample.databinding.CharactersListItemBinding
import ankit.com.starwarssample.model.CharacterPresentationModel

/**
 * Created by AnkitSingh on 12/12/20.
 */
class StarWarCharactersAdapter (private val listener: OnClickHandler):
        ListAdapter<CharacterPresentationModel, RecyclerView.ViewHolder>(CharacterDiffCallback()) {

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
        fun bind(listener: OnClickHandler, item: CharacterPresentationModel) {
            binding.apply {
                character = item
                executePendingBindings()
            }
        }
    }

    interface OnClickHandler {
        fun onItemClick(character: CharacterPresentationModel)
    }
}