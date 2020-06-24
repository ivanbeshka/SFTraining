package com.example.sftraining.ui.registration

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.sftraining.R

class EnterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_activity)

        if (!onBoardingFinished()) findNavController(R.id.nav_fragment_enter).navigate(R.id.navKnowingFragment)

    }

    //if user already finished knowing part
    private fun onBoardingFinished(): Boolean{
        val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}