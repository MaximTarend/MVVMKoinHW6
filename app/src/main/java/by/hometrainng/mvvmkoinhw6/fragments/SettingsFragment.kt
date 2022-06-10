package by.hometrainng.mvvmkoinhw6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import by.hometrainng.mvvmkoinhw6.databinding.FragmentSettingsBinding
import by.hometrainng.mvvmkoinhw6.manager.SharedPrefsManager
import by.hometrainng.mvvmkoinhw6.model.NightMode
import org.koin.android.ext.android.inject

class SettingsFragment: Fragment() {

    private var _binding : FragmentSettingsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val prefsManager: SharedPrefsManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSettingsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            when(prefsManager.nightMode) {
                NightMode.DARK -> buttonDarkMode
                NightMode.LIGHT -> buttonLightMode
                NightMode.SYSTEM -> buttonSystemMode
            }.isChecked = true

            modeSwitcher.setOnCheckedChangeListener { _, buttonId ->
                when(buttonId) {
                    buttonDarkMode.id -> {
                        prefsManager.nightMode = NightMode.DARK
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    buttonLightMode.id -> {
                        prefsManager.nightMode = NightMode.LIGHT
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    buttonSystemMode.id -> {
                        prefsManager.nightMode = NightMode.SYSTEM
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}