package com.epitech.cashmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)
    }

    fun launchScan(view: View) {
        val scanner = IntentIntegrator(this)
        scanner.initiateScan()
    }

    fun goToSettings(view: View) {
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG + 2).show()
                } else {
                    Toast.makeText(this, "1 Scanned: " + result.contents, Toast.LENGTH_LONG + 2).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

}
