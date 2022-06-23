package by.hometrainng.mvvmkoinhw6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.hometrainng.mvvmkoin6.domain.model.Beer
import by.hometrainng.mvvmkoin6.domain.model.BeerDetails
import by.hometrainng.mvvmkoinhw6.R
import by.hometrainng.mvvmkoinhw6.databinding.FragmentRandomBeerBinding
import by.hometrainng.mvvmkoinhw6.model.LceState
import by.hometrainng.mvvmkoinhw6.viewModels.RandomBeerViewModel
import coil.load
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomBeerFragment: Fragment() {

    private var _binding : FragmentRandomBeerBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val randomBeerViewModel by viewModel<RandomBeerViewModel>()

    private var beerID = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentRandomBeerBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            buttomRandom.setOnClickListener{
                randomBeerViewModel.onClickedRandom()
            }
            randomBeerViewModel
                .loadRandomFlow
                .onEach {
                    when(it) {
                        is LceState.Content<BeerDetails> -> {
                            val beer = it.data
                            beerID = beer.id
                            name.text = beer.name
                            image.load(beer.imageURL)
                            textView.text = beer.description
                        }
                        is LceState.Error -> {
                            Snackbar.make(
                                root,
                                it.throwable.message ?: "Error",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        LceState.Loading -> { }
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            toolbar.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.info -> {
                        findNavController().navigate(
                            RandomBeerFragmentDirections.toInfo(beerID)
                        )
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}