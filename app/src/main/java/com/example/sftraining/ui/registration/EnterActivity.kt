package com.example.sftraining.ui.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.sftraining.R
import com.example.sftraining.ui.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EnterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var intentMain: Intent

    companion object {
        private const val RC_SIGN_IN = 9001

        fun isValidEmail(email: String): Boolean {
            val regex = Regex("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
            return email.matches(regex)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_activity)

        intentMain = Intent(this, MainActivity::class.java)

        firebaseAuth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        if (onBoardingFinished()) findNavController(R.id.nav_fragment_enter).navigate(
            R.id.navChooseEnterTypeFragment,
            null,
            NavOptions.Builder().setPopUpTo(R.id.navKnowingFragment, true).build()
        )

    }

    fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    intentMain.putExtra("user_type", "google")
                    startActivity(intentMain)
                    finish()

                } else {
                    Toast.makeText(this, "Registration fail", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun anonRegister() {
        firebaseAuth.signInAnonymously().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                intentMain.putExtra("user_type", "anon")
                startActivity(intentMain)
                finish()

            } else {
                Toast.makeText(this, "Registration fail", Toast.LENGTH_SHORT).show()
            }

        }
    }

    //if user already finished knowing part
    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}