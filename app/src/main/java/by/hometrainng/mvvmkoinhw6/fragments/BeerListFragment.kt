package by.hometrainng.mvvmkoinhw6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.hometrainng.mvvmkoinhw6.R
import by.hometrainng.mvvmkoinhw6.adapters.BeerListAdapter
import by.hometrainng.mvvmkoinhw6.databinding.FragmentBeerDetailsBinding
import by.hometrainng.mvvmkoinhw6.databinding.FragmentBeerListBinding
import by.hometrainng.mvvmkoinhw6.repository.BeerRepository
import by.hometrainng.mvvmkoinhw6.room.AppDatabase
import by.hometrainng.mvvmkoinhw6.viewModels.ListViewModel
import by.hometrainng.oroutineshw5.extentions.addPaginationScrollListener
import by.hometrainng.oroutineshw5.extentions.addSpaceDecoration
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BeerListFragment : Fragment() {

    private var _binding: FragmentBeerListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val listViewModel by viewModel<ListViewModel>()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BeerListAdapter(requireContext()) { beer ->
            findNavController()
                .navigate(
                    BeerListFragmentDirections.toDetails(beer.id)
                )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentBeerListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val linearLayoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.addSpaceDecoration(SPACE)
            recyclerView.addPaginationScrollListener(linearLayoutManager, ITEMS_TO_LOAD) {
                listViewModel.onLoadMore()
            }

            listViewModel
                .dataFlow
                .onEach {
                    adapter.submitList(it)
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE = 16
        private const val ITEMS_TO_LOAD = 16
    }
}