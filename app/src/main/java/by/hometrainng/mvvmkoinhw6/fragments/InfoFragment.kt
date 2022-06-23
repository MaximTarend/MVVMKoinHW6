package by.hometrainng.mvvmkoinhw6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.hometrainng.mvvmkoin6.domain.model.BeerDetails
import by.hometrainng.mvvmkoinhw6.databinding.InfoFragmentBinding
import by.hometrainng.mvvmkoinhw6.model.LceState
import by.hometrainng.mvvmkoinhw6.viewModels.DetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class InfoFragment : BottomSheetDialogFragment() {

    private var _binding: InfoFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args by navArgs<InfoFragmentArgs>()

    private val detailsViewModel by viewModel<DetailsViewModel> {
        parametersOf(args.beerId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return InfoFragmentBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        detailsViewModel
            .loadDetailsFlow
            .onEach { lce ->
                when (lce) {
                    is LceState.Content<BeerDetails> -> {
                        val beer = lce.data
                        with(binding) {
                            name.text = beer.name
                            foodPairing0.text = beer.foodPairing[0]
                            foodPairing1.text = beer.foodPairing[1]
                            foodPairing2.text = beer.foodPairing[2]
                            brewersTips.text = beer.brewersTips
                        }
                    }
                    is LceState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            lce.throwable.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}