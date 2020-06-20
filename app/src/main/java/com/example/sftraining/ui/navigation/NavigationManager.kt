package com.example.sftraining.ui.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.scopes.ActivityScoped

class NavigationManager(private val fragmentManager: FragmentManager, private val container: Int) {

    var navigationListener: (() -> Unit)? = null

    init {
        fragmentManager.addOnBackStackChangedListener {
            navigationListener?.invoke()
        }
    }

    fun open(fragment: Fragment) {
        openFragment(fragment, addToBackStack = true, isRoot = false)
    }

    fun openAsRoot(fragment: Fragment){
        openFragment(fragment, addToBackStack = false, isRoot = true)
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean, isRoot: Boolean) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (isRoot)
            fragmentTransaction.replace(container, fragment, "ROOT")
        else
            fragmentTransaction.replace(container, fragment)

//        fragmentTransaction.setCustomAnimations()

        if (addToBackStack) fragmentTransaction.addToBackStack(fragment.toString())

        fragmentTransaction.commit()
    }

}