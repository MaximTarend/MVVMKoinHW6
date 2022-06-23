package by.hometrainng.mvvmkoinhw6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import by.hometrainng.mvvmkoin6.domain.model.BeerDetails
import by.hometrainng.mvvmkoinhw6.R
import by.hometrainng.mvvmkoinhw6.databinding.FragmentBeerDetailsBinding
import by.hometrainng.mvvmkoinhw6.model.LceState
import by.hometrainng.mvvmkoinhw6.viewModels.DetailsViewModel
import coil.load
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BeerDetailsFragment: Fragment() {

    private var _binding: FragmentBeerDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var beerID = 1

    private val args by navArgs<BeerDetailsFragmentArgs>()

    private val detailsViewModel by viewModel<DetailsViewModel> {
        parametersOf(args.beerId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentBeerDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            toolbar.setupWithNavController(findNavController())

            detailsViewModel
                .loadDetailsFlow
                .onEach {
                    when(it) {
                        is LceState.Content<BeerDetails> -> {
                            val beer = it.data
                            beerID = beer.id
                            image.load(beer.imageURL)
                            name.text = beer.name
                            description.text = beer.description
                            tagline.text = beer.tagline
                        }
                        is LceState.Error -> {
                            Snackbar.make(
                                root,
                                it.throwable.message ?: "Error",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            toolbar.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.info -> {
                        findNavController().navigate(
                            BeerDetailsFragmentDirections.toInfo(beerID)
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