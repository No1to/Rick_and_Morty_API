package com.example.rick_and_morty_api.ui.activity.character

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rick_and_morty_api.CartoonCharacter
import com.example.rick_and_morty_api.R
import com.example.rick_and_morty_api.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(CHARACTER_ID_ARG, 0)

        viewModel.getData(id).observe(this) {
            it?.let {
                setCharacterData(it)
            }
        }
    }

    private fun setCharacterData(it: CartoonCharacter) = with(binding) {
        Log.e("ololo", "Data is not null")
        tvCharacterName.text = it.name
        tvStatus.text = it.status
        Glide.with(imageCharacter).load(it.image).into(imageCharacter)// load(it.image).circleCrop()

        when (it.status) {
            "Alive" -> binding.circleStatus.setBackgroundResource(R.drawable.bg_green_circle)
            "Dead" -> binding.circleStatus.setBackgroundResource(R.drawable.bg_red_circle)
            "unknown" -> binding.circleStatus.setBackgroundResource(R.drawable.bg_circle)
        }
    }

    companion object {
        const val CHARACTER_ID_ARG = "characterIdArg"
    }

}