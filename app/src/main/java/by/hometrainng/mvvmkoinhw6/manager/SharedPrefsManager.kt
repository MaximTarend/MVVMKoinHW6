package by.hometrainng.mvvmkoinhw6.manager

import android.content.Context

import by.hometrainng.mvvmkoinhw6.delegate.PrefsDelegate


class SharedPrefsManager(context: Context) {

    private val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    var nightMode: by.hometrainng.mvvmkoinhw6.model.NightMode by enumPref(KEY_MODE, by.hometrainng.mvvmkoinhw6.model.NightMode.LIGHT)

    private inline fun <reified E : Enum<E>> enumPref(key: String, defaultValue: E) =
        PrefsDelegate(
            sharedPrefs,
            getValue = { getString(key, null)?.let(::enumValueOf) ?: defaultValue },
            setValue = { putString(key, it.name) }
        )

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val KEY_MODE = "mode"
    }
}
