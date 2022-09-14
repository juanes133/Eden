package com.jadevelopers.eden

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.*
import android.net.ConnectivityManager.NetworkCallback
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import java.lang.reflect.Type

class CheckNetworkConnection(private val connectivityManager: ConnectivityManager) :
    LiveData<Boolean>() {
    constructor(application: Application) : this(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    private val networkCallback = object : NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

   @RequiresApi(Build.VERSION_CODES.M)
    fun isConnected(function: () -> Unit): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
       if (capabilities != null){
           if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
            }
        }
        return true
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}