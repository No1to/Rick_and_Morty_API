package com.example.rick_and_morty_api.ui.activity.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rick_and_morty_api.CartoonModel
import com.example.rick_and_morty_api.databinding.ItemCardBinding
import com.example.rick_and_morty_api.utils.Status
import java.util.Locale

class CharactersAdapter(
    private val onCharacterClick: (Int) -> Unit
) : ListAdapter<CartoonModel, CharactersAdapter.CharactersViewHolder>(
    CharacterDiffCallback()
) {
    // private var charactersList = listOf<CartoonModel>()
    // private var characters: List<CartoonCharacter> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharactersViewHolder(
            binding, onCharacterClick
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    /***/
    class CharactersViewHolder(
        private val binding: ItemCardBinding,
        private val onCharacterClick: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(character: CartoonModel) {
            with(binding) {
                tvCharacterName.text = character.name
                tvCharacterStatus.text = character.status
                tvCharacterLocation.text = character.location.name
                tvCharacterSpecies.text = character.species

                Glide.with(binding.ivCharacter).load(character.image).into(binding.ivCharacter)

                itemCharacterContent.setOnClickListener {
                    onCharacterClick(character.characterId)
                }

                when (Status.valueOf(character.status.uppercase(Locale.getDefault()))) {
                    Status.ALIVE -> binding.ivCharacterStatus.setBackgroundResource(
                        Status.ALIVE.drawableResource
                    )

                    Status.DEAD -> binding.ivCharacterStatus.setBackgroundResource(
                        Status.DEAD.drawableResource
                    )

                    Status.UNKNOWN -> binding.ivCharacterStatus.setBackgroundResource(
                        Status.UNKNOWN.drawableResource
                    )
                }

                /*when (character.status) {
                    "Alive" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_green_circle)
                    "Dead" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_red_circle)
                    "unknown" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_grey_circle)
                }*/
            }
        }
    }
    /***/
}

class CharacterDiffCallback : DiffUtil.ItemCallback<CartoonModel>() {
    /*
    override fun areItemsTheSame(oldItem: CartoonModel, newItem: CartoonModel) = oldItem.characterId == newItem.characterId
    булька
    override fun areContentsTheSame(oldItem: CartoonModel, newItem: CartoonModel) = oldItem == newItem
    */

    override fun areItemsTheSame(oldItem: CartoonModel, newItem: CartoonModel): Boolean {
        return oldItem.characterId == newItem.characterId
    }

    override fun areContentsTheSame(oldItem: CartoonModel, newItem: CartoonModel): Boolean {
        return oldItem == newItem
    }

}