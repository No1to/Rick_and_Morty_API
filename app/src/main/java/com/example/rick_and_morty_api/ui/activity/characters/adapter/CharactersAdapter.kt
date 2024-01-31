package com.example.rick_and_morty_api.ui.activity.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rick_and_morty_api.CartoonCharacter
import com.example.rick_and_morty_api.R
import com.example.rick_and_morty_api.databinding.ItemCharacterCardBinding

class CharactersAdapter(private val onCharacterClick: (Int) -> Unit) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    private var charactersList = listOf<CartoonCharacter>()
//    private var characters: List<CartoonCharacter> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterCardBinding.inflate(
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
        holder.OnBind(charactersList[position])
    }

    fun setCharacter(list: List<CartoonCharacter>) {
        charactersList = list
        notifyDataSetChanged()
    }

}

class CharacterViewHolder(
    private val binding: ItemCharacterCardBinding,
    private val onCharacterClick: (Int) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun OnBind(character: CartoonCharacter) {
        binding.tvName.text = character.name
        binding.tvStatus.text = character.status
        Glide.with(binding.ivChar).load(character.image).into(binding.ivChar)

        binding.cardView.setOnClickListener {
            onCharacterClick(character.characterId)
        }

        when (character.status) {
            "Alive" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.bg_green_circle)
            "Dead" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.bg_red_circle)
            "unknown" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.bg_circle)
        }
    }

}