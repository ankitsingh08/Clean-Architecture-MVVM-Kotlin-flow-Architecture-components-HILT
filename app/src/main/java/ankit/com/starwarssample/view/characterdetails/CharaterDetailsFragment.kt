package ankit.com.starwarssample.view.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ankit.com.starwarssample.databinding.CharacterDetailsBinding
import ankit.com.starwarssample.model.CharacterPresentationModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by AnkitSingh on 12/12/20.
 */
@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private val characterDetailsViewModel: CharacterDetailsViewModel by viewModels()

    private lateinit var binding: CharacterDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailsBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner { lifecycle }
        initializeUI()
        return binding.root
    }

    private fun initializeUI() {
        val character = arguments?.getParcelable<CharacterPresentationModel>("character")

        (activity as? AppCompatActivity)?.supportActionBar?.title = character?.name

        binding.characterDetailViewModel = characterDetailsViewModel
        binding.characterModel = character

        val planetUrl = character?.homeworld ?: ""
        val speciesUrl = character?.species ?: emptyList()
        val filmUrl = character?.films ?: emptyList()

        characterDetailsViewModel.getCharacterDetails(planetUrl, speciesUrl, filmUrl)

        val speciesAdapter =
            SpeciesAdapter()
        binding.rvSpecies.adapter = speciesAdapter

        characterDetailsViewModel.species.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                binding.tvSpeciesUnavailable.visibility = View.GONE
                binding.rvSpecies.visibility = View.VISIBLE
                speciesAdapter.submitList(it)
            } else {
                binding.tvSpeciesUnavailable.visibility = View.VISIBLE
                binding.rvSpecies.visibility = View.GONE
            }

        })

        val filmAdapter =
            FilmsAdapter()
        binding.rvMovies.adapter = filmAdapter

        characterDetailsViewModel.films.observe(viewLifecycleOwner, Observer {
            filmAdapter.submitList(it)
        })
    }

}