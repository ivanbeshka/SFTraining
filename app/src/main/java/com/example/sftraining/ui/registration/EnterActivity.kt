package com.example.sftraining.ui.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlin.Exception

@AndroidEntryPoint
class EnterActivity : BaseActivity() {

    companion object {
        const val TAG = "EnterActivity"
    }

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
//        startActivityForResult(signInIntent, RC_SIGN_IN)

        val contract = ActivityResultContracts.StartActivityForResult()

        registerForActivityResult(contract) {
            if (it.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account)

                    Log.d(TAG, "signInWithGoogle: success")
                } catch (e: ApiException) {
                    Log.e(TAG, "signInWithGoogle: ${e.message}", e)
                }
            }
        }.launch(signInIntent)
    }

    //
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.result!!
//                firebaseAuthWithGoogle(account.idToken!!)
//            } catch (e: ApiException) {
//                Log.e(TAG, "signInWithGoogle: ${e.message}", e)
//            }
//        }
//    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {

        startLoadingAnimation()

        val credential = GoogleAuthProvider.getCredential(account?.idToken ?: "", null)
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

                createUser(user, false)

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
    private fun createUser(user: User, startMain: Boolean = true) {
        enterViewModel.createUser(user, {
            stopLoadingAnimation()
            if (startMain) {
                startActivity(intentMain)
                finish()
            }
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