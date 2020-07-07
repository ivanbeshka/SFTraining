package com.example.sftraining.ui.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.sftraining.R
import com.example.sftraining.model.User
import com.example.sftraining.ui.base.BaseActivity
import com.example.sftraining.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterActivity : BaseActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var intentMain: Intent
    private val enterViewModel: EnterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_activity)

        setProgressIndicatorLayout(R.id.loading_indicator_layout_enter)

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
        val contract = ActivityResultContracts.StartActivityForResult()

        //new result api
        val getAcc = registerForActivityResult(contract) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            val account = task.result!!
            firebaseAuthWithGoogle(account.idToken!!)
        }

        getAcc.launch(signInIntent)
    }

    fun registration(email: String, pass: String, name: String = "") {

        startLoadingAnimation()

        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val user = User(
                    uid = firebaseAuth.uid.toString(),
                    email = firebaseAuth.currentUser?.email.toString(),
                    name = name
                )
                //create user in db
                enterViewModel.createUser(user,
                    onFailure = {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    })

                stopLoadingAnimation()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.register_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        startLoadingAnimation()

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = User(
                        uid = firebaseAuth.uid.toString(),
                        email = firebaseAuth.currentUser?.email.toString()
                    )

                    intentMain.putExtra("user_type", "google")

                    createUser(user)

                } else {
                    stopLoadingAnimation()

                    Toast.makeText(this, "Registration fail", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun login(email: String, pass: String) {

        startLoadingAnimation()

        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                stopLoadingAnimation()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                stopLoadingAnimation()

                Toast.makeText(this, R.string.auth_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun anonRegister() {

        startLoadingAnimation()

        firebaseAuth.signInAnonymously().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                val user = User(
                    uid = firebaseAuth.uid.toString(),
                    anon = true
                )

                createUser(user)

                stopLoadingAnimation()

                intentMain.putExtra("user_type", "anon")
                startActivity(intentMain)
                finish()
            } else {
                stopLoadingAnimation()

                Toast.makeText(this, "Registration fail", Toast.LENGTH_SHORT).show()
            }

        }
    }

    //create user in db
    private fun createUser(user: User) {
        enterViewModel.createUser(user, {
            stopLoadingAnimation()
            startActivity(intentMain)
            finish()
        }, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    //if user already finished knowing part
    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}