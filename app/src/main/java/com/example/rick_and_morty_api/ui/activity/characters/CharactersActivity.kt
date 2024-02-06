package com.example.rick_and_morty_api.ui.activity.characters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_and_morty_api.databinding.ActivityCharactersBinding
import com.example.rick_and_morty_api.ui.activity.character.CharacterActivity
import com.example.rick_and_morty_api.ui.activity.characters.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }

    //     private val charactersAdapter = CharactersAdapter(this::onClickItem)
    private val charactersAdapter by lazy {
        CharactersAdapter(this::onClickItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        viewModel.getCharacters().observe(this) { characters ->
            charactersAdapter.setCharacters(characters)
        }
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(
            this@CharactersActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = charactersAdapter
    }

    private fun onClickItem(characterId: Int) {
        startActivity(
            Intent(
                this,
                CharacterActivity::class.java
            ).apply {
                putExtra(CharacterActivity.CHARACTER_ID_ARG, characterId)
            }
        )
        /*val intent = Intent(this, CharacterActivity::class.java)
          intent.putExtra(AnyClass.CHARACTER_ID_ARG, characterId)
          startActivity(intent)*/
    }

    companion object {
        const val CHARACTER_ID_NAVIGATE = "characterId"
    }

}