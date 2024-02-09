package com.example.rick_and_morty_api.ui.activity.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rick_and_morty_api.CartoonModel
import com.example.rick_and_morty_api.R
import com.example.rick_and_morty_api.databinding.ActivityCharacterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding

    // import org.koin.androidx.viewmodel.ext.android.viewModel
    private val viewModel: CharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(CHARACTER_ID_ARG, 0)

        viewModel.getData(id).observe(this) {
            it?.let {
                setCharacterData(it)
            }
        }

    }

    private fun setCharacterData(it: CartoonModel) = with(binding) {
        tvCharacterName.text = it.name
        tvCharacterStatus.text = it.status
        tvGenderAnswer.text = it.gender
        tvCharacterLocation.text = it.location.name
        tvCharacterSpecies.text = it.species
        tvCharacterFirstSeen.text = it.origin.name

        Glide.with(ivCharacterImage).load(it.image)
            .into(ivCharacterImage)// load(it.image).circleCrop()


        when (it.status) {
            "Alive" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_green_circle)
            "Dead" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_red_circle)
            "unknown" -> binding.ivCharacterStatus.setBackgroundResource(R.drawable.bg_grey_circle)
        }
    }

    companion object {
        const val CHARACTER_ID_ARG = "characterIdArg"
    }

}