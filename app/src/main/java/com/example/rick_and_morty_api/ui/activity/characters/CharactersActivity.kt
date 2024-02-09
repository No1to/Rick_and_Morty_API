package com.example.rick_and_morty_api.ui.activity.characters

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_and_morty_api.databinding.ActivityCharactersBinding
import com.example.rick_and_morty_api.ui.activity.character.CharacterActivity
import com.example.rick_and_morty_api.ui.activity.characters.adapter.CharactersAdapter
import com.example.rick_and_morty_api.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    /*private val viewModel by lazy {
         ViewModelProvider(this)[CharactersViewModel::class.java]
     }*/

    // import org.koin.androidx.viewmodel.ext.android.viewModel
    private val viewModel by viewModel<CharactersViewModel>()
    // private val viewModel: CharactersViewModel by viewModels()

    // private val charactersAdapter = CharactersAdapter(this::onClickItem)
    private val charactersAdapter by lazy {
        CharactersAdapter(this::onClickItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        viewModel.getCharacters().observe(this) { state ->
            binding.progressCircular.isVisible = state is Resource.Loading
            when (state) {
                is Resource.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                }

                is Resource.SuccLoading -> {
                    if (state.data != null) {
                        charactersAdapter.submitList(state.data)
                    }
                }
            }
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

}