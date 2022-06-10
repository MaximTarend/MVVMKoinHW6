package by.hometrainng.mvvmkoinhw6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import by.hometrainng.mvvmkoinhw6.manager.SharedPrefsManager
import by.hometrainng.mvvmkoinhw6.model.NightMode
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val prefsManager: SharedPrefsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(
            when (prefsManager.nightMode) {
                NightMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                NightMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                NightMode.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )

        setContentView(R.layout.activity_main)
    }

}

