package com.example.sftraining.ui.app_settings

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.sftraining.R
import java.util.*


class AppSettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var themeSetting: String
    private lateinit var lightTheme: String
    private lateinit var darkTheme: String
    private lateinit var languageSetting: String
    private lateinit var languageRu: String
    private lateinit var languageEn: String
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_settings_fragment, rootKey)

//        theme settings
        themeSetting = getString(R.string.setting_theme)
        lightTheme = getString(R.string.light_theme)
        darkTheme = getString(R.string.dark_theme)

//        language settings
        languageSetting = getString(R.string.setting_language)
        languageRu = getString(R.string.language_ru)
        languageEn = getString(R.string.language_en)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        if (p1 == themeSetting) {
            when (p0?.getString(themeSetting, R.string.light_theme.toString()).toString()) {
                lightTheme -> activity?.setTheme(R.style.ThemeOverlay_AppCompat_Light)
                darkTheme -> activity?.setTheme(R.style.ThemeOverlay_AppCompat_Dark)
            }
        } else if (p1 == languageSetting) {
        }
        when (p0?.getString(themeSetting, R.string.light_theme.toString()).toString()) {
            languageRu -> changeLanguage("ru")
            languageEn -> changeLanguage("en")
        }
    }

    private fun changeLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        val config = Configuration(requireContext().resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)

        context?.resources?.updateConfiguration(
            config,
            context?.resources?.displayMetrics
        )
    }
}