package com.epitech.cashmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun testCo(view: View) {
        Toast.makeText(this, inputServerIP.text, Toast.LENGTH_LONG + 2).show()
    }
}
