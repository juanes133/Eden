package com.jadevelopers.eden

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jadevelopers.eden.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var dialog:  AlertDialog? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var checkNetworkConnection: CheckNetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callNetworkConnection()
        dialog = AlertDialog.Builder(this)
            .setTitle("No Internet Connection")
            .setMessage("Please check your internet connection and try again")
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
}