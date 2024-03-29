package com.jadevelopers.eden.features.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentLoginBinding
import com.jadevelopers.eden.features.base.EdenFragment

class LoginFragment : EdenFragment() {

    private var auth: FirebaseAuth? = null
    private var googleSignInClient: GoogleSignInClient? = null
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        context
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        edenActivity.supportActionBar?.title = ""
        edenActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        edenActivity.supportActionBar?.hide()
        binding.icIcon.animation =
            AnimationUtils.loadAnimation(context, R.anim.scroll_down_image_text_login)
        binding.eden.animation =
            AnimationUtils.loadAnimation(context, R.anim.scroll_down_image_text_login)
        binding.btnGoogle.setOnClickListener {
            signIn()
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()

        activity?.let {
            googleSignInClient = GoogleSignIn.getClient(it, gso)
            auth = Firebase.auth
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth?.currentUser
        updateUI(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        activity?.let {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth?.let { auth ->
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithCredential:success")
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.exception)
                            updateUI(null)
                        }
                    }
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            activity?.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}