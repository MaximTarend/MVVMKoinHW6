package by.hometrainng.mvvmkoinhw6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.hometrainng.mvvmkoinhw6.R
import by.hometrainng.mvvmkoinhw6.databinding.FragmentBottomNavBinding

class BottomNavFragment: Fragment() {

    private var _binding: FragmentBottomNavBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentBottomNavBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val bottomNavController = (childFragmentManager.findFragmentById(R.id.page_container) as NavHostFragment)
                .navController
            bottomNavigation.setupWithNavController(bottomNavController)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}