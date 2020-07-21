package com.example.sftraining.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.sftraining.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView

class BottomNavigation : BottomSheetDialogFragment() {

    private lateinit var navigationView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_nav_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView = view.findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.favorite -> {
                    findNavController(this).popBackStack(R.id.navListOfFavoriteExers, true)
                    findNavController(this).navigate(R.id.navListOfFavoriteExers)
                    dialog?.hide()
                }

                R.id.profile -> {
                    findNavController(this).popBackStack(R.id.navAccSettingsFragment, true)
                    findNavController(this).navigate(R.id.navAccSettingsFragment)
                    dialog?.hide()
                }

                R.id.all_exers -> {
                    findNavController(this).popBackStack(R.id.navListOfExers, true)
                    findNavController(this).navigate(R.id.navListOfExers)
                    dialog?.hide()
                }

                R.id.trainings -> {
                    findNavController(this).popBackStack(R.id.navListOfTrainings, true)
                    findNavController(this).navigate(R.id.navListOfTrainings)
                    dialog?.hide()
                }

                R.id.settings -> {
                    findNavController(this).popBackStack(R.id.navAppSettingsFragment, true)
                    findNavController(this).navigate(R.id.navAppSettingsFragment)
                    dialog?.hide()
                }
            }

            true
        }
    }
}