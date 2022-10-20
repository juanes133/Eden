package com.jadevelopers.eden

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.utilities.CheckNetworkConnection
import com.jadevelopers.eden.databinding.ActivityMainBinding
import com.jadevelopers.eden.features.productslist.viewmodel.ProductsViewModel
import com.jadevelopers.eden.features.productslist.viewmodel.ProductsViewModel.ProductsViewModelFactory

class EdenActivity : AppCompatActivity() {
    private var dialog: AlertDialog? = null
    private lateinit var binding: ActivityMainBinding
    private var checkNetworkConnection: CheckNetworkConnection? = null
    private var drawerLayout: DrawerLayout? = null
    private var appBarConfiguration: AppBarConfiguration? = null
    private var navController: NavController? = null
    private var navigationView: NavigationView? = null
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        fullScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callNetworkConnection()
        dialog = AlertDialog.Builder(this)
            .setTitle("Sin conexion de internet")
            .setMessage("Comprueba tu conexión a Internet e inténtalo de nuevo")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .create()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView
        appBarConfiguration = navController?.let {
            AppBarConfiguration(it.graph, drawerLayout)
        }

        appBarConfiguration?.let { appBarConfiguration ->
            navController?.let {
                setupActionBarWithNavController(it, appBarConfiguration)
            }
        }
        binding.btnShopping.setOnClickListener {
            findNavController(R.id.fragmentContainerView).navigate(R.id.shoppingFragment)
        }
        binding.btnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            fullScreen()
            findNavController(R.id.fragmentContainerView).navigate(R.id.action_productsFragment_to_loginFragment)
            drawerLayout?.closeDrawers()
        }

        if (checkNetworkConnection?.isConnected(this) == false) {
            dialog?.setCancelable(false)
            dialog?.show()
        }
    }

    private fun fullScreen() {
        if (Firebase.auth.currentUser == null) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        val navController = findNavController(R.id.fragmentContainerView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayout?.closeDrawers()
            false
        } else {
            appBarConfiguration?.let { navController.navigateUp(it) } == true
        }
    }

    private fun callNetworkConnection() {
        checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection?.observe(this) { isConnected ->
            if (isConnected) {
                dialog?.dismiss()
            } else {
                dialog?.setCancelable(false)
                dialog?.show()
            }
        }
    }

    override fun onBackPressed() {
        if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayout?.closeDrawer(GravityCompat.START)
        } else if (Firebase.auth.currentUser == null) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}

