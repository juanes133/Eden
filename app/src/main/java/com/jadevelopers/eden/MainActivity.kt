package com.jadevelopers.eden

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var dialog: AlertDialog? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var checkNetworkConnection: CheckNetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callNetworkConnection()
        dialog = AlertDialog.Builder(this)
            .setTitle("Sin conexion de internet")
            .setMessage("Comprueba tu conexión a Internet e inténtalo de nuevo")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .create()
    }

    private fun callNetworkConnection() {
        checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this) { isConnected ->
            if (isConnected) {
                dialog?.dismiss()
            } else {
                dialog?.setCanceledOnTouchOutside(false)
                dialog?.show()
            }
        }
    }

    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        if (navController.currentDestination?.id == R.id.homeFragment) {
            if (Firebase.auth.currentUser != null){
                finish()
            }else{
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}
