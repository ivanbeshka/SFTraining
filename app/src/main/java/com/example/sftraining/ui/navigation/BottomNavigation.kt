package com.example.sftraining.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.sftraining.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_nav_sheet.*

class BottomNavigation : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_nav_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigation_view.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.favorite -> { findNavController(this).navigate(R.id.navListOfFavoriteExers)
                    dialog?.hide()
                }

                R.id.profile -> {
                    findNavController(this).navigate(R.id.navAccSettingsFragment)
                    dialog?.hide()
                }

                R.id.all_exers -> {
                    findNavController(this).navigate(R.id.navListOfExers)
                    dialog?.hide()
                }
            }

            true
        }
    }
}