package com.example.sftraining.ui.app_settings

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.preference.PreferenceFragmentCompat
import com.example.sftraining.R
import java.util.*


class AppSettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

//    private lateinit var btnExitAcc: MaterialButton
//    private lateinit var tilPersonalInfo: TextInputLayout
//    private lateinit var etPersonalInfo: TextInputEditText
//    private lateinit var tvPersonalInfo: MaterialTextView
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
        if(p1 == themeSetting){
            when (p0?.getString(themeSetting, R.string.light_theme.toString()).toString()){
                lightTheme -> activity?.setTheme(R.style.ThemeOverlay_AppCompat_Light)
                darkTheme -> activity?.setTheme(R.style.ThemeOverlay_AppCompat_Dark)
            }
        }
//        else if(p1 == languageSetting){
//            when (p0?.getString(themeSetting, R.string.light_theme.toString()).toString()){
//                languageRu -> activity?.loca(R.style.ThemeOverlay_AppCompat_Light)
//                languageEn -> activity?.setTheme(R.style.ThemeOverlay_AppCompat_Dark)
//            }
//        }
    }

//    fun setLocale(lang: String) {
//        val myLocale = Locale(lang)
//        val res: Resources = resources
//        val dm: DisplayMetrics = res.displayMetrics
//        val conf: Configuration = res.configuration
//        conf.locale = myLocale
//        res.updateConfiguration(conf, dm)
//        val refresh = Intent(this, AndroidLocalize::class.java)
//        activity?.finish()
//        startActivity(refresh)
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.app_settings_fragment, container, false)
//
//        initView(root)
//
//        return root
//    }

//    override fun initView(root: View) {
//        btnExitAcc = root.findViewById(R.id.btn_acc_exit)
//        tilPersonalInfo = root.findViewById(R.id.til_personal_info)
//        etPersonalInfo = root.findViewById(R.id.et_person_info)
//        tvPersonalInfo = root.findViewById(R.id.tv_personal_info)
//    }
}