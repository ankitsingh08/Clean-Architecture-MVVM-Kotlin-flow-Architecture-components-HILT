package ankit.com.starwarssample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ankit.com.starwarssample.databinding.CharacterDetailsBinding
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
        initializeUI()
        return binding.root
    }

    private fun initializeUI() {

        val planetUrl = arguments?.getString("planet") ?: ""
        val speciesUrl = arguments?.getString("species") ?: ""
        val filmUrl = arguments?.getString("film") ?: ""
        characterDetailsViewModel.getCharacterDetails(planetUrl, speciesUrl, filmUrl)
    }

}