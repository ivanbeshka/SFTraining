package com.example.sftraining.ui.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sftraining.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class StartActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Launcher)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        auth = Firebase.auth


    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        routeToAppropriatePage(currentUser)
    }

    private fun routeToAppropriatePage(user: FirebaseUser?){
        if (user == null) {

        }
    }
}