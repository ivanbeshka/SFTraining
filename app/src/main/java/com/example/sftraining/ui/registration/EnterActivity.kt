package com.example.sftraining.ui.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.sftraining.R
import com.example.sftraining.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EnterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_activity)

        firebaseAuth = Firebase.auth

        if (!onBoardingFinished()) findNavController(R.id.nav_fragment_enter).navigate(R.id.navKnowingFragment)

    }

    //if user already finished knowing part
    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    fun anonRegister() {
        firebaseAuth.signInAnonymously().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user_type", "anon")
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Registration fail", Toast.LENGTH_SHORT).show()
            }

        }
    }
}