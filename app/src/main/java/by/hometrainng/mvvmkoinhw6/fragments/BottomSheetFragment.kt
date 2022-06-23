package by.hometrainng.mvvmkoinhw6.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.hometrainng.mvvmkoin6.domain.model.BeerDetails
import by.hometrainng.mvvmkoin6.domain.model.Brewery
import by.hometrainng.mvvmkoinhw6.R
import by.hometrainng.mvvmkoinhw6.databinding.BottomSheetInfoBinding
import by.hometrainng.mvvmkoinhw6.model.LceState
import by.hometrainng.mvvmkoinhw6.viewModels.BottomSheetInfoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BottomSheetFragment: BottomSheetDialogFragment() {

    private var _binding : BottomSheetInfoBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args by navArgs<BottomSheetFragmentArgs>()

    private val bottomSheetInfoViewModel by viewModel<BottomSheetInfoViewModel> {
        parametersOf(args.breweryID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return BottomSheetInfoBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            bottomSheetInfoViewModel
                .loadInfoFlow
                .onEach {
                    when(it) {
                        is LceState.Content<Brewery> -> {
                            val brewery = it.data
                            title.text = brewery.name
                            textLink.text = brewery.websiteUrl
                            textLocation.text = "${brewery.latitude}, ${brewery.longitude}"
                            phoneNumber.text = brewery.phone
                        }
                        is LceState.Error -> {
                            Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            textLink.setOnClickListener {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(textLink.text.toString()))
                startActivity(webIntent)
            }
            phoneNumber.setOnClickListener {
                val phoneIntent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:${textLink.text}"))
                startActivity(phoneIntent)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "BottomSheetFragment"
    }
}