package com.example.sftraining.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sftraining.R
import com.example.sftraining.ui.MainActivity
import com.example.sftraining.ui.registration.RegistrationActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

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
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}