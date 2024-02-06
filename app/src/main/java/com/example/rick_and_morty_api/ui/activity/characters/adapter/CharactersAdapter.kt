package com.example.rick_and_morty_api.ui.activity.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rick_and_morty_api.CartoonModel
import com.example.rick_and_morty_api.R
import com.example.rick_and_morty_api.databinding.ItemCardBinding

class CharactersAdapter(private val onCharacterClick: (Int) -> Unit) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    private var charactersList = listOf<CartoonModel>()
//    private var characters: List<CartoonCharacter> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CharacterViewHolder(
            binding,
            onCharacterClick
        )
    }

    override fun getItemCount(): Int = charactersList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(charactersList[position])
    }

    fun setCharacters(list: List<CartoonModel>) {
        charactersList = list
        notifyDataSetChanged()
    }

}

class CharacterViewHolder(
    private val binding: ItemCardBinding,
    private val onCharacterClick: (Int) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {

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

            when (character.status) {
                "Alive" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_green_circle)
                "Dead" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_red_circle)
                "unknown" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_grey_circle)
            }
        }
    }

}